# 📱 App Features Overview

This document outlines the main features of the app based on the current fragment structure and commit history.

> ⚠️ Note: All fragments currently have the same last commit message:  
> "_belum bisa tambah gambar masih sulit_" (Image upload not yet implemented).

---

## 📂 Fragment Overview

| Fragment File              | Purpose                              |
|----------------------------|--------------------------------------|
| `LoginFragment.kt`         | User login UI and logic              |
| `MainFragment.kt`          | Likely the home screen/dashboard     |
| `ProfileFragment.kt`       | User profile display & editing       |
| `RegisterFragment.kt`      | New user registration                |
| `ResetPasswordFragment.kt` | Reset forgotten password             |
| `StatusFragment.kt`        | Possibly a user status update feed   |
| `UserFragment.kt`          | View/search user list or details     |  |

---

## 🔍 Feature Breakdown (Planned or In Progress)

### ✅ Core Features (Available)
- **User Authentication**
  - Login
  - Registration
  - Password reset
- **User Navigation**
  - Home/Main screen
  - Fragment-based UI structure

---

### 🚧 In Progress / Pending Features
- **Image Uploading**
  - Possibly for profile pictures or post images
  - Currently marked as "still difficult"

---

### 🧩 Suggested Features to Add
- Profile picture upload (via `ProfileFragment.kt`)
- Post image sharing or status media (via `StatusFragment.kt`)
- Error handling + input validation
- UI polish and consistent design across fragments
- Migration from `jcenter()` to `mavenCentral()` in Gradle
- Use of Jetpack libraries (e.g. `ViewModel`, `LiveData`, `Navigation Component`)

---

## 📅 Commit Insight

- **Last commit message** (across all fragments):  
  `"belum bisa tambah gambar masih sulit"`
- **Last commit date**: ~5 years ago  
  This indicates the image upload functionality is a known challenge and hasn't been addressed yet.

---

## 📌 Next Steps

- [ ] Implement image selection (e.g. from gallery or camera)
- [ ] Integrate image upload (e.g. Firebase Storage or REST API)
- [ ] Provide image preview before upload
- [ ] Add progress indicators and error handling
- [ ] Test across different Android versions

---

> 🛠️ **Pro Tip**: Start image implementation in `ProfileFragment.kt` as it's a common entry point for profile pictures.

