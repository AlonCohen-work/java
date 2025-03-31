# 📱 WhatsApp Auto Sender API

This project allows you to send **automatic WhatsApp messages** using `whatsapp-web.js`.
It supports **immediate** and **scheduled** message delivery via a simple REST API
built with **Node.js** and **Express**. All messages are stored in **MongoDB Atlas**.

---

## 🚀 Project Features

- ✅ Send WhatsApp messages via API  
- 🕒 Schedule messages for future delivery  
- 💾 Store messages in MongoDB Atlas (`sent`, `pending`, `failed`)  
- 🧠 Automatic WhatsApp reconnection handling  
- 🔐 Environment configuration using `.env`

---

## 📂 Installation & First Run

1. **Clone the project** or extract the ZIP:
   ```bash
   cd your-folder-name

npm install
Create a .env file in the root with:

MONGO_URI=mongodb+srv://yourUsername:yourPassword@cluster0.mongodb.net/yourDatabase
PORT=3010

Start the server:
npm start

📤 Sending a Message (via Postman)
POST http://localhost:3010/send-message
Content-Type: application/json
{
  "phone_number": "+972509999999", 
  "message": "Hello from API!"
}
or 
{
  "phone_number": "+972509999999",
  "message": "Hello! This is a scheduled message.",
  "scheduled_time": "2025-04-01 18:00:00"
}
you can change the phone the message and time to your need
