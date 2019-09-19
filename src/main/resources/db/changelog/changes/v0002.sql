-- Table: public.products

-- DROP TABLE public.products;

CREATE TABLE public.products
(
    "ProductID"   integer                           NOT NULL,
    "ProductName" text COLLATE pg_catalog."default" NOT NULL,
    "ProductType" text COLLATE pg_catalog."default" NOT NULL,
    "Description" integer                           NOT NULL,
    "Price"       text COLLATE pg_catalog."default",
    "ImageSource" text COLLATE pg_catalog."default",
    CONSTRAINT products_pkey PRIMARY KEY ("ProductID"),
    CONSTRAINT "products_ProductID_key" UNIQUE ("ProductID")

)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.products
    OWNER to postgres;