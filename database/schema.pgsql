create sequence users_id_seq start 1;
create table users(
	id integer primary key default nextval('users_id_seq'),
	name varchar,
	login varchar,
	password varchar
);

create sequence bills_id_seq start 1;
create table bills(
	id integer primary key default nextval('bills_id_seq'),
	name varchar,
	user_id integer foreign key users(id)
);


create sequence bills_users_id_seq start 1;
create table bills_users(
	id integer primary key default nextval('bills_users_id_seq'),
	bill_id integer foreign key bills(id),
	user_id integer foreign key users(id)
);
create index

