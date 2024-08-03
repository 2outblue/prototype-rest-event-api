Prototype/test Event Service API for my Softuni Spring Advanced project.

Endpoint - /api/event
Supports:
GET /api/event - returns all events
GET /api/event/{uuid} - return Event with that uuid
POST /api/event - creates a new Event, returns the URI to the new Event, expects a JSON object with uuid, description, name, date, tickets number(int), hallUuid, userUuid
PATCH /api/event/{uuid} - updates Event info - can provide name, description and ticket number - if not all of these are provided, only the specified fields are updated, the rest remain the same
DELETE /api/event/{uuid} - deletes an Event
