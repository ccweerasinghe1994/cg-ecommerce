create schema cgecommercedb collate utf8mb4_0900_ai_ci;

grant alter, alter routine, create, create routine, create temporary tables, create view, delete, drop, event, execute, index, insert, lock tables, references, select, show view, trigger, update on cgecommercedb.* to cgecommerceuser;

use cgecommercedb;

drop table if exists cgecommercedb.categories;

drop table if exists cgecommercedb.categories_seq;


# Hibernate:
drop table if exists categories;
# Hibernate:
create table categories
(
    category_id   bigint       not null auto_increment,
    category_name varchar(100) not null,
    primary key (category_id)
) engine = InnoDB;
# Hibernate:
alter table categories
    add constraint UK41g4n0emuvcm3qyf1f6cn43c0 unique (category_name)


-- let's add 50 categories


INSERT INTO categories (category_name) VALUES 
('Electronics'),
('Clothing'),
('Home & Garden'),
('Books'),
('Sports & Outdoors'),
('Beauty & Personal Care'),
('Toys & Games'),
('Automotive'),
('Health & Wellness'),
('Jewelry'),
('Pet Supplies'),
('Food & Beverages'),
('Office Supplies'),
('Musical Instruments'),
('Art & Crafts'),
('Baby Products'),
('Tools & Hardware'),
('Computers & Accessories'),
('Cameras & Photography'),
('Mobile Phones'),
('Furniture'),
('Kitchen & Dining'),
('Watches'),
('Shoes'),
('Bags & Luggage'),
('Video Games'),
('Movies & TV'),
('Music'),
('Grocery'),
('Appliances'),
('Fitness Equipment'),
('Outdoor Living'),
('Party Supplies'),
('Travel Gear'),
('Bath & Bedding'),
('Collectibles'),
('Industrial Supplies'),
('Smart Home'),
('Lighting'),
('Holiday & Seasonal'),
('Stationery'),
('Digital Downloads'),
('Safety & Security'),
('Educational Supplies'),
('Wedding Shop'),
('Vintage & Antiques'),
('Garden Tools'),
('Window Treatments'),
('Plumbing'),
('Storage & Organization');