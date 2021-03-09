create table if not exists Ingredient
(
    id   varchar(4)  not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists Taco
(
    id        identity,
    name      varchar(50) not null,
    placedAt timestamp   not null
);

create table if not exists TacoIngredients
(
    taco       bigint     not null,
    ingredient varchar(4) not null
);

alter table TacoIngredients
    add foreign key (taco) references Taco (id);
alter table TacoIngredients
    add foreign key (ingredient) references Ingredient (id);

create table if not exists TacoOrder
(
    id             identity,
    deliveryDate   varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity   varchar(50) not null,
    deliveryState  varchar(2)  not null,
    deliveryZip    varchar(10) not null,
    ccNumber       varchar(16) not null,
    ccExpiration   varchar(5)  not null,
    ccCVV          varchar(3)  not null,
    placedAt       timestamp   not null
);

create table if not exists TacoOrderTacos
(
    tacoOrder bigint not null,
    taco      bigint not null
);

alter table TacoOrderTacos
    add foreign key (tacoOrder) references TacoOrder (id);
alter table TacoOrderTacos
    add foreign key (taco) references Taco (id);