CREATE TABLE public."Accounts"
(
    id serial,
	balance double precision NOT NULL,
    last_name character varying(50),
    first_name character varying(50) NOT NULL,
    middle_name character varying(50),
    PRIMARY KEY (id)
);

CREATE TABLE public."AllTransactions"
(
    id serial,
    user_sender integer,
    amount double precision NOT NULL,
    user_recipient integer,
    PRIMARY KEY (id)
);

ALTER TABLE public."AllTransactions"
    ADD FOREIGN KEY (user_sender)
    REFERENCES public."Accounts" (id)
    NOT VALID;