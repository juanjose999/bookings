# API Documentation: Bookings CRUD
## Documentation swagger UI
- **Base URL:** `https://bookings-8hdw.onrender.com/swagger-ui/index.html`

## Overview
This API allows users to perform CRUD (Create, Read, Update, Delete) operations on bookings. Bookings represent trips from an origin location to a destination. Each booking can be associated with a user.

![diagrama bd mongo](https://github.com/juanjose999/bookings/blob/main/bookings.png?raw=true)


## Endpoints
- **Base URL:** `https://bookings-8hdw.onrender.com/v1/bookings`

1. **POST /bookings**
   - *Description:* Creates a new booking with user information.
   - *Request Body (JSON):*
     ```json
     {
       "originLocation": "Cucuta",
       "destination": "Bogota",
       "departureTime": "2024-05-12",
       "departureHour": "02:20 AM",
       "hoursTripDuration": 1,
       "seatNumber": "13",
       "userData": {
         "firstName": "Alberto",
         "lastName": "Corredor",
         "email": "alo@gmail.com"
       }
     }
     ```
   - *Response:* Returns the created booking object with a unique ID.

2. **PUT /bookings/{bookingId}**
   - *Description:* Updates an existing booking identified by {bookingId}.
   - *Request Body (JSON):*
     ```json
     {
       "originLocation": "Cucuta",
       "destination": "Bogota",
       "departureTime": "2024-05-12",
       "departureHour": "02:20 AM",
       "hoursTripDuration": 1,
       "seatNumber": "13",
       "userData": {
         "firstName": "Alberto",
         "lastName": "Corredor",
         "email": "alo@gmail.com"
       }
     }
     ```
   - *Response:* Returns the updated booking object.

3. **DELETE /bookings/{bookingId}**
   - *Description:* Deletes the booking identified by {bookingId}.
   - *Response:* No content (204) if successful.

## Booking JSON Schema
```json
{
  "originLocation": "string",
  "destination": "string",
  "departureTime": "string (YYYY-MM-DD)",
  "departureHour": "string (HH:MM AM/PM)",
  "hoursTripDuration": "integer",
  "seatNumber": "string",
  "userData": {
    "firstName": "string",
    "lastName": "string",
    "email": "string"
  }
}
