drop table car cascade;

CREATE TABLE public.car (
                            id integer NOT NULL,
                            registration character varying(255),
                            carmodel_id integer,
                            client_id integer
);

INSERT INTO public.car (id, registration, carmodel_id, client_id) VALUES (15, 'morning', NULL, NULL);