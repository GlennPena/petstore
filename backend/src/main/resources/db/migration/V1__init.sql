create table pet_listings (
  id bigserial primary key,
  name varchar(255) not null,
  category varchar(32) not null,
  age_months integer not null,
  price numeric(12,2) not null,
  availability varchar(32) not null,
  description text,
  image_url varchar(512),
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now()
);

create index idx_pet_listings_category on pet_listings(category);
create index idx_pet_listings_availability on pet_listings(availability);
create index idx_pet_listings_price on pet_listings(price);
