create sequence users_id_seq start 1;
create table users(
	id integer not null primary key default nextval('users_id_seq'),
	login varchar not null unique,
	password_hash varchar not null
);
create index users_id_idx on users(id);

create sequence bills_id_seq start 1;
create table bills(
	id integer not null primary key default nextval('bills_id_seq'),
	name varchar not null,
	user_id integer not null references  users(id)
);
create index bills_id_idx on bills(id);


create sequence bills_users_id_seq start 1;
create table bills_users(
	id integer primary key default nextval('bills_users_id_seq'),
	bill_id integer not null references bills(id),
	user_id integer not null references users(id)
);
create index bills_users_user_id_idx on bills_users(user_id);
create index bills_users_bill_id_idx on bills_users(bill_id);


create sequence expenses_id_seq start 1;
create table expenses(
	id integer not null primary key default nextval('expenses_id_seq'),
	name varchar not null,
	amount numeric not null check(amount > 0),
	user_id integer not null references  users(id),
	bill_id integer not null references  bills(id)
);
create index expenses_id_idx on bills(id);

create sequence expenses_users_id_seq start 1;
create table expenses_users(
	id integer primary key default nextval('expenses_users_id_seq'),
	expense_id integer not null references expenses(id),
	weight integer not null default 1,
	user_id integer not null references users(id)
);
create index expenses_users_user_id_idx on expenses_users(user_id);
create index expenses_users_expense_id_idx on expenses_users(expense_id);
