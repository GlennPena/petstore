# Data Model: Petstore Commerce

## Pet Listing

Represents a pet shown in the storefront and managed through the admin API.

Fields:
- `id`: Unique identifier
- `name`: Display name of the pet
- `category`: Dog, cat, bird, or fish
- `age`: Age in months or a normalized age value
- `price`: Current catalog price
- `availability`: Available or unavailable state
- `description`: Short listing description
- `imageUrl`: Optional image reference
- `createdAt`: Record creation timestamp
- `updatedAt`: Record update timestamp

Rules:
- Name and category are required.
- Price must be non-negative.
- Availability must be explicit.
- Unavailable pets remain in the catalog but must not be presented as available to shoppers.

Relationships:
- Can be referenced by cart items and wishlist items.

## Cart Item

Represents a pet saved by a shopper for later review.

Fields:
- `id`: Unique identifier
- `petListingId`: Reference to a pet listing
- `quantity`: Number of times the item appears in the cart context, if the design supports quantity
- `addedAt`: Timestamp when the item was saved

Rules:
- Cart items should point to a valid pet listing.
- Duplicate saves should not create confusing duplicate behavior.

## Wishlist Item

Represents a pet a shopper wants to track without treating it as a purchase-ready item.

Fields:
- `id`: Unique identifier
- `petListingId`: Reference to a pet listing
- `savedAt`: Timestamp when the item was added

Rules:
- Wishlist entries should remain easy to add and remove.

## State Notes

- Pet listings transition between available and unavailable states.
- CRUD operations are authoritative through the backend API.
- Cart and wishlist state are shopper-facing and should not mutate the core pet listing record.