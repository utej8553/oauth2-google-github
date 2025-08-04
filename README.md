# oauth2-google-github
# Spring Boot OAuth2 Login (Google + GitHub)

## Setup

### 1️⃣ Google OAuth2
1. Go to [Google Cloud Console](https://console.cloud.google.com/).
2. Create an **OAuth 2.0 Client ID** (type: Web application).
3. Set:
   - **Authorized JavaScript origins**:  
     ```
     http://localhost:8080
     ```
   - **Authorized redirect URIs**:  
     ```
     http://localhost:8080/login/oauth2/code/google
     ```
4. Copy **Client ID** and **Client Secret**.

---

### 2️⃣ GitHub OAuth2
1. Go to [GitHub Developer Settings → OAuth Apps](https://github.com/settings/developers).
2. Click **New OAuth App**.
3. Set:
   - **Homepage URL**:  
     ```
     http://localhost:8080
     ```
   - **Authorization callback URL**:  
     ```
     http://localhost:8080/login/oauth2/code/github
     ```
4. Copy **Client ID** and **Client Secret**.

---

### 3️⃣ Add to `application.properties`
```properties
spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET
spring.security.oauth2.client.registration.google.scope=email,profile

spring.security.oauth2.client.registration.github.client-id=YOUR_GITHUB_CLIENT_ID
spring.security.oauth2.client.registration.github.client-secret=YOUR_GITHUB_CLIENT_SECRET
spring.security.oauth2.client.registration.github.scope=user:email


```
---
## How It Works

We configure Spring Security using a SecurityFilterChain.  
In this configuration, we allow public access to static files like the HTML, CSS, and JS files.  
All other requests require the user to be logged in.  
We enable OAuth2 login, which automatically provides login URLs for each provider:
- `/oauth2/authorization/google` for Google login
- `/oauth2/authorization/github` for GitHub login

We also set a default page to open after login, so the user is redirected there once they are authenticated.

---

## Getting User Details

After a user logs in, Spring Security stores their profile information in an OAuth2User object.  
We can access this information in a controller method by asking Spring to inject the currently logged-in user.  
From there, we can get their name, email, and profile picture.  
The frontend can call an endpoint like `/user` to fetch and display these details.
