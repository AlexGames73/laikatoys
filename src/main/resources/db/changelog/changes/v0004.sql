-- Table: public.transactions

-- DROP TABLE public.transactions;

CREATE TABLE public.transactions
(
    "TransactionID" integer NOT NULL,
    "Quantity" integer NOT NULL,
    "ApprovalStatus" text COLLATE pg_catalog."default" NOT NULL,
    "MemberID" integer NOT NULL,
    "ProductID" integer NOT NULL,
    CONSTRAINT transactions_pkey PRIMARY KEY ("TransactionID"),
    CONSTRAINT "transactions_TransactionID_key" UNIQUE ("TransactionID")
    ,
    CONSTRAINT "transactions_membersID" FOREIGN KEY ("MemberID")
        REFERENCES public.members ("MemberID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "transactions_productsID" FOREIGN KEY ("ProductID")
        REFERENCES public.products ("ProductID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.transactions
    OWNER to postgres;

-- Index: fki_transactions_membersID

-- DROP INDEX public."fki_transactions_membersID";

CREATE INDEX "fki_transactions_membersID"
    ON public.transactions USING btree
        ("MemberID")
    TABLESPACE pg_default;

-- Index: fki_transactions_productsID

-- DROP INDEX public."fki_transactions_productsID";

CREATE INDEX "fki_transactions_productsID"
    ON public.transactions USING btree
        ("ProductID")
    TABLESPACE pg_default;