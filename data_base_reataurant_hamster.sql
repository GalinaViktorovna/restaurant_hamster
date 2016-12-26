use restaurant_hamster;

create table ingredient(
	id int primary key not null auto_increment,
	name varchar(40),
    quantity int
	);

create table image(
	id int primary key not null auto_increment,
	file mediumblob
	);
    
    create table positions(
	id int primary key not null auto_increment,
	name varchar(40) 
    );
    
create table employee(
	id int primary key not null auto_increment,
	name char(50) not null,
	age int,
    salary int,
	positions_id int ,
	photo_id int, 
    dtype varchar(60),
	foreign key(positions_id) 
    references positions(id),
    foreign key(photo_id)
    references image(id)
	);
    
create table dish(
	id int primary key not null auto_increment,
	name varchar(40),
    description varchar(40),
    photo_id int,
    weight int,
    price int,
    foreign key(photo_id)
    references image(id)
	);
    
    create table dish_components(
	id int primary key not null auto_increment,
	dish_id int,
    ingredient_id int,
    quantity int,
    foreign key(dish_id)
    references dish(id),
    foreign key(ingredient_id)
    references ingredient(id)
	);
    
    create table menu(
    id int primary key not null auto_increment,
    name varchar(40),
    photo_id int,
    foreign key(photo_id)
    references image(id)
    );
    
    create table menu_content(
    menu_id int,
    dish_id int,
    foreign key(menu_id)
    references menu(id),
    foreign key(dish_id)
    references dish(id)
    );
    
    create table order_info(
    id int primary key not null auto_increment,
    table_number int,
    date varchar(40),
    waiter int,
    closed boolean,
    foreign key(waiter)
    references employee(id)
    );
   
     create table kitchen_journal(
	id int primary key not null auto_increment,
	dish_id int,
    date varchar(40),
    order_info_id int,
    cook_id int,
    foreign key(dish_id)
    references dish(id),
    foreign key(order_info_id)
    references order_info(id),
    foreign key(cook_id)
    references employee(id)
	);
    
    create table order_content(
    id int primary key not null auto_increment,
    order_info_id int,
    dish_id int,
    quantity int,
    foreign key(order_info_id)
    references order_info(id),
    foreign key(dish_id)
    references dish(id)
    );
    
    
    
