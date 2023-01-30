-- Table: public.Movies

-- DROP TABLE IF EXISTS public."Movies";

CREATE TABLE IF NOT EXISTS public."Movies"
(
    id bigint NOT NULL DEFAULT 'nextval('"Movie_id_seq"'::regclass)',
    duration integer NOT NULL,
    title character varying(70) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Movie_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Movies"
    OWNER to mariakuzmina;

-- Table: public.Sessions

-- DROP TABLE IF EXISTS public."Sessions";

CREATE TABLE IF NOT EXISTS public."Sessions"
(
    id bigint NOT NULL DEFAULT 'nextval('"Session_id_seq"'::regclass)',
    session_date date NOT NULL,
    movie_id bigint NOT NULL,
    ticket_price numeric(12,2) NOT NULL,
    session_time time without time zone NOT NULL,
    CONSTRAINT "Session_pkey" PRIMARY KEY (id),
    CONSTRAINT uk_date_time_movie UNIQUE (session_date, session_time, movie_id),
    CONSTRAINT "FK_session_movie" FOREIGN KEY (movie_id)
        REFERENCES public."Movies" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Sessions"
    OWNER to mariakuzmina;

-- Index: fki_FK_session_movie

-- DROP INDEX IF EXISTS public."fki_FK_session_movie";

CREATE INDEX IF NOT EXISTS "fki_FK_session_movie"
    ON public."Sessions" USING btree
    (movie_id ASC NULLS LAST)
    TABLESPACE pg_default;


-- Table: public.Tickets

-- DROP TABLE IF EXISTS public."Tickets";

CREATE TABLE IF NOT EXISTS public."Tickets"
(
    id bigint NOT NULL DEFAULT 'nextval('"Tickets_id_seq"'::regclass)',
    "number" integer,
    session_id bigint NOT NULL,
    CONSTRAINT "Tickets_pkey" PRIMARY KEY (id),
    CONSTRAINT uk_session_ticket_number UNIQUE ("number", session_id),
    CONSTRAINT fk_tickets_sessions FOREIGN KEY (session_id)
        REFERENCES public."Sessions" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Tickets"
    OWNER to mariakuzmina;
-- Index: fki_fk_tickets_sessions

-- DROP INDEX IF EXISTS public.fki_fk_tickets_sessions;

CREATE INDEX IF NOT EXISTS fki_fk_tickets_sessions
    ON public."Tickets" USING btree
    (session_id ASC NULLS LAST)
    TABLESPACE pg_default;


-- insert data
INSERT INTO public."Movies"(
	title, duration)
	VALUES ('Люди Х', 90),
		('Аватар', 120),
		('Чебурашка', 60);

INSERT INTO public."Sessions" ( movie_id, session_date, session_time, ticket_price) 
VALUES 
	(2, '1/15/2023', '14:00:00', 450),
	(3, '1/15/2023', '15:30:00', 200),
	(1, '1/15/2023', '18:00:00', 300),
	(2, '1/15/2023', '19:00:00', 500);
	
INSERT INTO public."Tickets" (session_id, "number")
VALUES (1, 1),
	(1, 2),
	(1, 4),
	(1, 7),
	(2, 1),
	(2, 3),
	(2, 11),
	(3, 1),
	(3, 2),
	(3, 4),
	(4, 7),
	(4, 1),
	(4, 3),
	(5, 11),
	(5, 1);