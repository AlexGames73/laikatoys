-- Table: public.carts

-- DROP TABLE public.carts;

CREATE TABLE public.carts
(
    "CartID"    integer NOT NULL,
    "Quantity"  integer NOT NULL,
    "MemberID"  integer NOT NULL,
    "ProductID" integer NOT NULL,
    CONSTRAINT carts_pkey PRIMARY KEY ("CartID"),
    CONSTRAINT "carts_CartID_key" UNIQUE ("CartID"),
    CONSTRAINT "carts_MemberID_fkey" FOREIGN KEY ("MemberID")
        REFERENCES public.members ("MemberID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "carts_productsID" FOREIGN KEY ("ProductID")
        REFERENCES public.products ("ProductID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.carts
    OWNER to postgres;

-- Index: fki_cards_membersID

-- DROP INDEX public."fki_cards_membersID";

CREATE INDEX "fki_cards_membersID"
    ON public.carts USING btree
        ("MemberID")
    TABLESPACE pg_default;

-- Index: fki_carts_productsID

-- DROP INDEX public."fki_carts_productsID";

CREATE INDEX "fki_carts_productsID"
    ON public.carts USING btree
        ("ProductID")
    TABLESPACE pg_default;