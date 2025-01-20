create schema cgecommercedb collate utf8mb4_0900_ai_ci;

grant alter, alter routine, create, create routine, create temporary tables, create view, delete, drop, event, execute, index, insert, lock tables, references, select, show view, trigger, update on cgecommercedb.* to cgecommerceuser;

use cgecommercedb;

drop table if exists cgecommercedb.categories;

create table if not exists cgecommercedb.categories
(
    category_id   bigint auto_increment
        primary key,
    category_name varchar(100) not null,
    constraint UK41g4n0emuvcm3qyf1f6cn43c0
        unique (category_name)
);

create table if not exists cgecommercedb.product
(
    product_id    bigint auto_increment
        primary key,
    description   varchar(255) null,
    price         double       not null,
    product_name  varchar(255) null,
    quantity      int          null,
    special_price double       not null,
    category_id   bigint       null,
    image         varchar(255) null,
    discount      double       not null,
    constraint FKowomku74u72o6h8q0khj7id8q
        foreign key (category_id) references cgecommercedb.categories (category_id)
);


-- let's add 50 categories


INSERT INTO categories (category_name)
VALUES ('Electronics'),
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

-- let's add 1000 products

INSERT INTO product (description, price, product_name, quantity, special_price, category_id, image, discount)
VALUES ('The new iPhone 12 Pro Max with 256GB of storage', 1099.99, 'iPhone 12 Pro Max', 100, 999.99, 20,
        'https://www.apple.com/newsroom/images/product/iphone/standard/Apple_announce-iphone12pro_10132020_big.jpg.large.jpg',
        0.09),
       ('The new Samsung Galaxy S21 Ultra with 256GB of storage', 1199.99, 'Samsung Galaxy S21 Ultra', 100, 1099.99, 20,
        'https://www.samsung.com/us/smartphones/galaxy-s21-5g/buy/hero/hero-image.jpg', 0.08),
       ('The new MacBook Pro with M1 chip', 1299.99, 'MacBook Pro', 100, 1199.99, 13,
        'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/mbp-spacegray-select-202011?wid=892&hei=820&&qlt=80&.v=1603406905000',
        0.08),
       ('The new Dell XPS 13 with 11th Gen Intel Core i7', 1199.99, 'Dell XPS 13', 100, 1099.99, 13,
        'https://i.dell.com/is/image/DellContent//content/dam/global-site-design/product_images/dell_client_products/notebooks/xps_notebooks/13_9310/general/xps-13-9310-laptop-hero-504x350-ng.psd?fmt=jpg',
        0.08),
       ('The new Sony WH-1000XM4 Wireless Noise-Canceling Over-Ear Headphones', 349.99, 'Sony WH-1000XM4', 100, 299.99,
        13, 'https://www.sony.com/image/1b3b1f3b7b7', 0.14),
       ('The new Bose QuietComfort 35 II Wireless Bluetooth Headphones', 299.99, 'Bose QuietComfort 35 II', 100, 249.99,
        13,
        'https://www.bose.com/content/dam/Bose_DAM/Web/consumer_electronics/global/products/headphones/quietcomfort_35_ii_silver/product_silo_images/qc35_ii_silver_EC_hero.psd/_jcr_content/renditions/cq5dam.web.320.320.png',
        0.17),
       ('The new Apple Watch Series 6', 399.99, 'Apple Watch Series 6', 100, 349.99, 13,
        'https://store.storeimages.cdn-apple.com/4982/as-images.apple', 0.12),
       ('The new Samsung Galaxy Watch 3', 399.99, 'Samsung Galaxy Watch 3', 100, 349.99, 13,
        'https://www.samsung.com/us/wearables/galaxy-watch3/buy/hero/hero-image.jpg', 0.12),
       ('The new Canon EOS R5 Full-Frame Mirrorless Camera', 3899.99, 'Canon EOS R5', 100, 3499.99, 18,
        'https://www.usa.canon.com/internet/wcm/connect/us/1e4b3b1e-3b7b-4b7b-8b7b-7b7b7b7b7b7b/eos-r5-front.png?MOD=AJPERES&CACHEID=ROOTWORKSPACE-1e4b3b1e-3b7b-4b7b-8b7b-7b7b7b7b7b7b-mv3j6Z_',
        0.10),
       ('The new Nikon Z7 II Full-Frame Mirrorless Camera', 2999.99, 'Nikon Z7 II', 100, 2499.99, 18,
        'https://www.nikonusa.com/en/nikon-products/mirrorless-cameras/z-7-ii/_jcr_content/root/product-highlights/product-image-magnifier/product-image/image.img.jpg/1600079945791.jpg',
        0.17),
       ('The new Sony Alpha a7 III Full-Frame Mirrorless Camera', 1999.99, 'Sony Alpha a7 III', 100, 1499.99, 18,
        'https://www.sony.com/image/1b3b1f3b7b7', 0.25),
       ('The new Fujifilm X-T4 Mirrorless Camera', 1699.99, 'Fujifilm X-T4', 100, 1299.99, 18,
        'https://www.fujifilm.com/products', 0.25);


create table users
(
    username varchar(50)  not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);
create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);