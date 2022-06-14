# gypsee-assignment
- server port: 8080
- Used database Postgres
-  1) if table is not created automatically then use this script:
-  2) create table public.user(
	id bigint NOT NULL,
	name text NULL,
	email text NOT NULL,
	password text NOT NULL,
	is_active bool NULL,
	Constraint user_pkey PRIMARY KEY (id),
	constraint user_unkey UNIQUE (email)
)
- there are 5 apis:
-  1) get all user
-  2) signup a user
-  3) login a user
-  4) logout a user
-  5) signup and login a user simultaniously 
