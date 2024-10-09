CREATE TABLE IF NOT EXISTS USER(
    id BIGINT NOT NULL,
    city VARCHAR(255),
    fullname VARCHAR(255),
    password VARCHAR(255),
    phone_number VARCHAR(255),
    state VARCHAR(255),
    street VARCHAR(255),
    username VARCHAR(255),
    zip VARCHAR(255)
);

create table if not exists ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists taco (
    id identity,
    name varchar(50) not null,
    created_at timestamp not null
);

create table if not exists taco_ingredients (
    taco_id bigint not null,
    ingredient_id varchar(4) not null,
    foreign key (taco_id) references Taco(id),
    foreign key (ingredient_id) references ingredient(id)
);

create table if not exists taco_order (
    id identity,
    delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state varchar(2) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null,
    user_id bigint not null,
    foreign key (user_id) references user(id)
);

create table if not exists taco_order_tacos (
    taco_order bigint not null,
    taco bigint not null,
    foreign key (taco_order) references taco_order(id),
    foreign key (taco) references taco(id)
);
