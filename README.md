Java 17
Spring Boot
Spring Data JPA
postgresql
Maven
# 🏸 Badminton Shop Backend (Spring Boot)

## 📌 Overview
This is the backend service for an e-commerce system specializing in badminton products.  
It provides APIs for product management, cart handling, order processing, and VNPay payment integration.

---

## 🚀 Features

### 🛍 Product
- Create, update, delete product
- Manage product stock (quantity)
- Get product list

### 🛒 Cart
- Add product to cart
- Update quantity
- Remove item
- Auto merge duplicate items
- Validate stock before adding

### 📦 Order & Checkout
- Convert cart → order
- Calculate total price
- Maintain order status (PENDING, PAID, FAILED)

### 💳 Payment (VNPay)
- Generate VNPay payment URL
- Handle VNPay return callback
- Update order status after payment
- Deduct product stock after successful payment

---

## 🧠 System Flow

```text
Cart → Checkout → Order (PENDING)
     → VNPay Payment
     → Callback → Update Order (PAID / FAILED)
     → Deduct stock + Clear cart
