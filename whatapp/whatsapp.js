require('dotenv').config();
const express = require('express');
const cors = require('cors');
const { Client, LocalAuth } = require('whatsapp-web.js');
const mongojs = require('mongojs');
const qrcode = require('qrcode-terminal');
const schedule = require('node-schedule');

const app = express();
app.use(cors()); // Enable CORS for cross-origin requests
app.use(express.json()); // Allows server to read JSON requests
app.use(express.urlencoded({ extended: true })); // Allows URL-encoded data parsing

// Load environment variables
const MONGO_URI = process.env.MONGO_URI || "mongodb+srv://ever:23892389a@cluster0.arcpa.mongodb.net/whatapp?retryWrites=true&w=majority";
const PORT = process.env.PORT || 3010;

// Connect to MongoDB
const db = mongojs(MONGO_URI, ['messages']);
const messagesCollection = db.collection("messages");

// Initialize WhatsApp Web client
const client = new Client({ authStrategy: new LocalAuth() });

client.on('qr', qr => qrcode.generate(qr, { small: true }));
client.on('ready', () => console.log('✅ WhatsApp Client is ready!'));
client.on('disconnected', () => {
    console.log('🔄 WhatsApp Client disconnected. Attempting reconnection...');
    client.initialize();
});
client.initialize();

// Function to format and validate phone numbers
const formatPhoneNumber = (phone) => {
    let cleanedPhone = phone.replace(/\D/g, ""); // Remove non-numeric characters
    if (!cleanedPhone.startsWith("972")) {
        console.error("❌ Invalid phone number: Must start with country code (e.g., +972 for Israel).");
        return null;
    }
    return cleanedPhone + "@c.us";
};

// Function to send a WhatsApp message
const sendMessage = async (phone, message) => {
    const chatId = formatPhoneNumber(phone);
    if (!chatId) return false; // If number is invalid, do not send message

    try {
        console.log(`📨 Sending message to: ${chatId}`);
        await client.sendMessage(chatId, message);
        console.log(`✅ Message sent successfully to ${phone}`);
        return true;
    } catch (error) {
        console.error('❌ Error sending message:', error);
        return false;
    }
};

// API Endpoint to send messages
app.post('/send-message', async (req, res) => {
    console.log("Received request body:", req.body); // Debugging log

    res.setHeader("Access-Control-Allow-Origin", "*");
    res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
    res.setHeader("Access-Control-Allow-Headers", "Content-Type");
    
    const { phone_number, message, scheduled_time } = req.body;

    if (!phone_number || !message) {
        return res.status(400).json({ error: 'Missing phone_number or message' });
    }

    if (scheduled_time) {
        const msg = { phone_number, message, scheduled_time, status: 'pending' };
        messagesCollection.insert(msg, (err, doc) => {
            if (err) return res.status(500).json({ error: 'Database error' });

            schedule.scheduleJob(new Date(scheduled_time), async () => {
                const success = await sendMessage(phone_number, message);
                messagesCollection.update({ _id: doc._id }, { $set: { status: success ? 'sent' : 'failed' } });
            });

            res.json({ success: true, scheduled: true });
        });
    } else {
        const success = await sendMessage(phone_number, message);
        res.json({ success });
    }
});

// API Endpoint to retrieve messages
app.get('/messages', async (req, res) => {
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
    res.setHeader("Access-Control-Allow-Headers", "Content-Type");
    
    messagesCollection.find((err, docs) => {
        if (err) return res.status(500).json({ error: 'Database error' });
        res.json(docs);
    });
});

// Start the server
app.listen(PORT, () => console.log(`✅ Server running on port ${PORT}`));
