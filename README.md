# 📅 ระบบจองคิว Appointment Booking System API

โปรเจกต์นี้เป็นระบบจัดการการจองคิว (Appointment) และบริการ (Service) สำหรับร้านทั่วไป เช่น ร้านตัดผม คลินิก หรือร้านที่ต้องมีการนัดหมาย  
เขียนด้วย **Kotlin** และ **Ktor** มีการป้องกันไม่ให้จองเวลาซ้ำกันได้

---

## 🔧 ฟีเจอร์หลัก

- เพิ่ม/แก้ไข/ลบ บริการ (Service)
- จองคิว (Appointment) ได้
- ระบบเช็กว่าจองเวลาซ้ำหรือเปล่า (Double Booking)
- ทดสอบระบบด้วย Unit Test
- ส่งข้อมูลแบบ JSON

---

## 🛠️ เทคโนโลยีที่ใช้

- **ภาษา**: Kotlin  
- **Framework**: Ktor  
- **JSON**: kotlinx.serialization  
- **Test**: kotlin.test + JUnit  
- **Build**: Gradle

---
### 📋 Service (บริการ)

| Method | Endpoint         | ใช้ทำอะไร            |
|--------|------------------|-----------------------|
| GET    | `/services`      | ดูบริการทั้งหมด      |
| POST   | `/services`      | เพิ่มบริการใหม่       |
| PUT    | `/services/{id}` | แก้ไขบริการ           |
| DELETE | `/services/{id}` | ลบบริการ              |

### 📅 Appointment (จองคิว)

| Method | Endpoint            | ใช้ทำอะไร               |
|--------|----------------------|--------------------------|
| GET    | `/appointments`      | ดูคิวที่ถูกจองทั้งหมด     |
| POST   | `/appointments`      | จองคิวใหม่ (กันจองซ้ำ)   |
