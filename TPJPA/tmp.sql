--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2015-01-23 17:33:13 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 24890)
-- Name: personne; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE personne (
    id integer NOT NULL,
    datenaiss date DEFAULT '2010-01-01'::date,
    nom character varying(250),
    prenom character varying(250),
    tel character varying(10),
    password character varying
);


--
-- TOC entry 170 (class 1259 OID 24888)
-- Name: personne_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE personne_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1956 (class 0 OID 0)
-- Dependencies: 170
-- Name: personne_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE personne_id_seq OWNED BY personne.id;


--
-- TOC entry 1841 (class 2604 OID 24893)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY personne ALTER COLUMN id SET DEFAULT nextval('personne_id_seq'::regclass);


--
-- TOC entry 1844 (class 2606 OID 24898)
-- Name: personne_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT personne_pkey PRIMARY KEY (id);


-- Completed on 2015-01-23 17:33:13 CET

--
-- PostgreSQL database dump complete
--

