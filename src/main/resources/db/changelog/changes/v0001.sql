-- Table: public.members

-- DROP TABLE public.members;

CREATE TABLE public.members
(
    "MemberID" integer                           NOT NULL,
    "Username" text COLLATE pg_catalog."default" NOT NULL,
    "Password" text COLLATE pg_catalog."default" NOT NULL,
    "Email"    text COLLATE pg_catalog."default",
    "Fullname" text COLLATE pg_catalog."default",
    "Address"  text COLLATE pg_catalog."default",
    "Phone"    text COLLATE pg_catalog."default",
    CONSTRAINT members_pkey PRIMARY KEY ("MemberID"),
    CONSTRAINT "members_MemberID_key" UNIQUE ("MemberID")

)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.members
    OWNER to postgres;