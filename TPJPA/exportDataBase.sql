--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2015-02-10 11:43:52 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 176 (class 3079 OID 11756)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1981 (class 0 OID 0)
-- Dependencies: 176
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


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
-- TOC entry 1982 (class 0 OID 0)
-- Dependencies: 170
-- Name: personne_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE personne_id_seq OWNED BY personne.id;


--
-- TOC entry 174 (class 1259 OID 116049)
-- Name: formateur; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE formateur (
    id integer DEFAULT nextval('personne_id_seq'::regclass) NOT NULL,
    diplome character varying,
    datenaiss date DEFAULT '2010-01-01'::date,
    nom character varying(250),
    prenom character varying(250),
    tel character varying(10),
    password character varying(250)
);


--
-- TOC entry 173 (class 1259 OID 33108)
-- Name: promotion; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE promotion (
    id integer NOT NULL,
    libelle character varying(500),
    formateur_id integer
);


--
-- TOC entry 172 (class 1259 OID 33106)
-- Name: promotion_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE promotion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1983 (class 0 OID 0)
-- Dependencies: 172
-- Name: promotion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE promotion_id_seq OWNED BY promotion.id;


--
-- TOC entry 175 (class 1259 OID 116059)
-- Name: stagiaire; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE stagiaire (
    id integer DEFAULT nextval('personne_id_seq'::regclass) NOT NULL,
    experience character varying,
    promotion_id integer,
    datenaiss date DEFAULT '2010-01-01'::date,
    nom character varying(250),
    prenom character varying(250),
    tel character varying(10),
    password character varying(250)
);


--
-- TOC entry 1845 (class 2604 OID 24893)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY personne ALTER COLUMN id SET DEFAULT nextval('personne_id_seq'::regclass);


--
-- TOC entry 1847 (class 2604 OID 33111)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY promotion ALTER COLUMN id SET DEFAULT nextval('promotion_id_seq'::regclass);


--
-- TOC entry 1973 (class 0 OID 116049)
-- Dependencies: 174
-- Data for Name: formateur; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 1970 (class 0 OID 24890)
-- Dependencies: 171
-- Data for Name: personne; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO personne (id, datenaiss, nom, prenom, tel, password) VALUES (82, '1999-02-01', 'test', 'qsdsqd', '0205030409', NULL);
INSERT INTO personne (id, datenaiss, nom, prenom, tel, password) VALUES (10, '1926-05-31', 'Monroe', 'Marilyne2', NULL, NULL);
INSERT INTO personne (id, datenaiss, nom, prenom, tel, password) VALUES (15, NULL, 'Magne', 'Charles2', NULL, NULL);
INSERT INTO personne (id, datenaiss, nom, prenom, tel, password) VALUES (3, NULL, 'Monroe', 'MArilyne', NULL, NULL);
INSERT INTO personne (id, datenaiss, nom, prenom, tel, password) VALUES (99, NULL, 'user1', 'user1', NULL, NULL);
INSERT INTO personne (id, datenaiss, nom, prenom, tel, password) VALUES (98, NULL, 'test2', 'test2', NULL, NULL);


--
-- TOC entry 1984 (class 0 OID 0)
-- Dependencies: 170
-- Name: personne_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('personne_id_seq', 99, true);


--
-- TOC entry 1972 (class 0 OID 33108)
-- Dependencies: 173
-- Data for Name: promotion; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO promotion (id, libelle, formateur_id) VALUES (9, 'promo2', NULL);
INSERT INTO promotion (id, libelle, formateur_id) VALUES (10, 'promo3', NULL);
INSERT INTO promotion (id, libelle, formateur_id) VALUES (11, 'promo4', NULL);


--
-- TOC entry 1985 (class 0 OID 0)
-- Dependencies: 172
-- Name: promotion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('promotion_id_seq', 11, true);


--
-- TOC entry 1974 (class 0 OID 116059)
-- Dependencies: 175
-- Data for Name: stagiaire; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 1857 (class 2606 OID 116057)
-- Name: formateur_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY formateur
    ADD CONSTRAINT formateur_pkey PRIMARY KEY (id);


--
-- TOC entry 1853 (class 2606 OID 24898)
-- Name: personne_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT personne_pkey PRIMARY KEY (id);


--
-- TOC entry 1855 (class 2606 OID 33113)
-- Name: promotion_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY promotion
    ADD CONSTRAINT promotion_pkey PRIMARY KEY (id);


--
-- TOC entry 1859 (class 2606 OID 116067)
-- Name: stagiaire_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY stagiaire
    ADD CONSTRAINT stagiaire_pkey PRIMARY KEY (id);


--
-- TOC entry 1860 (class 2606 OID 116076)
-- Name: promotion_formateur_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY promotion
    ADD CONSTRAINT promotion_formateur_id_fkey FOREIGN KEY (formateur_id) REFERENCES formateur(id);


--
-- TOC entry 1861 (class 2606 OID 116068)
-- Name: stagiaire_promotion_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY stagiaire
    ADD CONSTRAINT stagiaire_promotion_id_fkey FOREIGN KEY (promotion_id) REFERENCES promotion(id);


-- Completed on 2015-02-10 11:43:53 CET

--
-- PostgreSQL database dump complete
--

