--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-07-18 17:36:43

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS "ticketToRideDb";
--
-- TOC entry 4851 (class 1262 OID 49152)
-- Name: ticketToRideDb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "ticketToRideDb" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';


ALTER DATABASE "ticketToRideDb" OWNER TO postgres;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4851 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 49163)
-- Name: city; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.city (
    id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.city OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 49156)
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.city_id_seq OWNER TO postgres;

--
-- TOC entry 4852 (class 0 OID 0)
-- Dependencies: 215
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;


--
-- TOC entry 221 (class 1259 OID 49174)
-- Name: price_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.price_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.price_id_seq OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 49251)
-- Name: price; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.price (
    id integer DEFAULT nextval('public.price_id_seq'::regclass) NOT NULL,
    segment_amount integer DEFAULT 0 NOT NULL,
    price integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.price OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 49171)
-- Name: route_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.route_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.route_id_seq OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 49188)
-- Name: route; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.route (
    id integer DEFAULT nextval('public.route_id_seq'::regclass) NOT NULL,
    segments text NOT NULL,
    "city_A" integer NOT NULL,
    "city_B" integer NOT NULL,
    price integer DEFAULT 5 NOT NULL,
    boundry_amount integer DEFAULT 1 NOT NULL,
    description text,
    currency text DEFAULT 'GBP'::text
);


ALTER TABLE public.route OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 49182)
-- Name: segment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.segment (
    id integer NOT NULL,
    city1 integer NOT NULL,
    city2 integer NOT NULL,
    boundry_amount integer DEFAULT 1 NOT NULL,
    segment_price integer DEFAULT 5 NOT NULL
);


ALTER TABLE public.segment OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 49170)
-- Name: segment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.segment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.segment_id_seq OWNER TO postgres;

--
-- TOC entry 4853 (class 0 OID 0)
-- Dependencies: 217
-- Name: segment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.segment_id_seq OWNED BY public.segment.id;


--
-- TOC entry 219 (class 1259 OID 49172)
-- Name: ticket_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ticket_id_seq OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 49205)
-- Name: ticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ticket (
    id integer DEFAULT nextval('public.ticket_id_seq'::regclass) NOT NULL,
    traveller_id integer NOT NULL,
    route_id integer NOT NULL,
    price integer NOT NULL
);


ALTER TABLE public.ticket OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 49173)
-- Name: traveller_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.traveller_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.traveller_id_seq OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 49196)
-- Name: traveller; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.traveller (
    id integer DEFAULT nextval('public.traveller_id_seq'::regclass) NOT NULL,
    name text NOT NULL,
    "moneyAmount" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.traveller OWNER TO postgres;

--
-- TOC entry 4659 (class 2604 OID 49175)
-- Name: city id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city ALTER COLUMN id SET DEFAULT nextval('public.city_id_seq'::regclass);


--
-- TOC entry 4660 (class 2604 OID 49185)
-- Name: segment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment ALTER COLUMN id SET DEFAULT nextval('public.segment_id_seq'::regclass);


--
-- TOC entry 4835 (class 0 OID 49163)
-- Dependencies: 216
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.city (id, name) FROM stdin;
1	BIRGMINGHAM
2	BRISTOL
3	COVENTRY
4	LONDON
5	NORTHHAMPTON
6	READING
7	SWINDON
\.


--
-- TOC entry 4845 (class 0 OID 49251)
-- Dependencies: 226
-- Data for Name: price; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.price (id, segment_amount, price) FROM stdin;
1	1	5
2	2	7
3	3	10
\.


--
-- TOC entry 4842 (class 0 OID 49188)
-- Dependencies: 223
-- Data for Name: route; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.route (id, segments, "city_A", "city_B", price, boundry_amount, description, currency) FROM stdin;
22	{2,8,6}	3	6	30	9	3x3 segments discount at price of 10x3	GBP
23	{2,8}	3	7	17	5	3 segments discount at price of 10 and 2 segments discount at price of 7	GBP
1	{1}	1	2	10	3	3 segments discount at price of 10	GBP
2	{2}	1	3	5	1	1 segment at price of 5	GBP
3	{3}	4	5	7	2	2 segments discount at price of 7	GBP
4	{4}	3	5	7	2	2 segments discount at price of 7	GBP
5	{5}	4	6	5	1	1 segment at price of 5	GBP
6	{6}	6	7	14	4	2x2 segments discount at price of 7x2	GBP
7	{7}	2	7	7	2	2 segments discount at price of 7	GBP
8	{8}	1	7	14	4	2x2 segments discount at price of 7x2	GBP
9	{7,8}	1	2	20	6	3x2 segments discount at price of 10x2	GBP
10	{1,7}	1	7	17	5	3 segments discount at price of 10 and 2 segments discount at price of 7	GBP
11	{2,4}	1	5	10	3	3 segments discount at price of 10	GBP
12	{2,4,3}	1	4	17	5	3 segments discount at price of 10 and 2 segments discount at price of 7	GBP
13	{8,6,5}	1	4	30	9	3x3 segments discount at price of 10x3	GBP
14	{7,6}	2	6	20	6	3x2 segments discount at price of 10x2	GBP
15	{7,6,5}	2	4	24	7	2x2 segments discount at price of 7x2 and 3 segments discount at price of 10	GBP
16	{1,2}	2	3	14	4	2x2 segments discount at price of 7x2	GBP
17	{1,2,4}	2	5	20	6	3x2 segments discount at price of 10x2	GBP
18	{1,8,6}	2	6	37	11	3x3 segments discount at price of 10x3 and 2 segments discount at price of 7	GBP
19	{8,6}	1	6	27	8	3x2 segments discount at price of 10x2 and 2 segments discount at price of 7	GBP
20	{1,8}	2	7	24	7	2x2 segments discount at price of 7x2 and 3 segments discount at price of 10	GBP
21	{4,3,5}	3	6	17	5	3 segments discount at price of 10 and 2 segments discount at price of 7	GBP
24	{2,1,7}	3	7	20	6	3x2 segments discount at price of 10x2	GBP
25	{3,5}	5	6	10	3	3 segments discount at price of 10	GBP
26	{3,5,6}	5	7	24	7	2x2 segments discount at price of 7x2 and 3 segments discount at price of 10	GBP
27	{4,2,8}	5	7	24	7	2x2 segments discount at price of 7x2 and 3 segments discount at price of 10	GBP
28	{1,7,6}	1	6	30	9	3x3 segments discount at price of 10x3	GBP
\.


--
-- TOC entry 4841 (class 0 OID 49182)
-- Dependencies: 222
-- Data for Name: segment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.segment (id, city1, city2, boundry_amount, segment_price) FROM stdin;
2	1	3	1	5
5	4	6	1	5
1	1	2	3	10
3	4	5	2	7
4	3	5	2	7
6	6	7	4	14
7	2	7	2	7
8	1	7	4	14
\.


--
-- TOC entry 4844 (class 0 OID 49205)
-- Dependencies: 225
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ticket (id, traveller_id, route_id, price) FROM stdin;
\.


--
-- TOC entry 4843 (class 0 OID 49196)
-- Dependencies: 224
-- Data for Name: traveller; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.traveller (id, name, "moneyAmount") FROM stdin;
1	TRAVELLER1	100
2	TRAVELLER2	15
3	TRAVELLER3	20
4	TRAVELLER4	200
\.


--
-- TOC entry 4854 (class 0 OID 0)
-- Dependencies: 215
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.city_id_seq', 7, true);


--
-- TOC entry 4855 (class 0 OID 0)
-- Dependencies: 221
-- Name: price_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.price_id_seq', 3, true);


--
-- TOC entry 4856 (class 0 OID 0)
-- Dependencies: 218
-- Name: route_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.route_id_seq', 28, true);


--
-- TOC entry 4857 (class 0 OID 0)
-- Dependencies: 217
-- Name: segment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.segment_id_seq', 8, true);


--
-- TOC entry 4858 (class 0 OID 0)
-- Dependencies: 219
-- Name: ticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ticket_id_seq', 1, false);


--
-- TOC entry 4859 (class 0 OID 0)
-- Dependencies: 220
-- Name: traveller_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.traveller_id_seq', 4, true);


--
-- TOC entry 4674 (class 2606 OID 49169)
-- Name: city cityIdPrimaryKey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT "cityIdPrimaryKey" PRIMARY KEY (id);


--
-- TOC entry 4684 (class 2606 OID 49258)
-- Name: price priceIdPrimaryKey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.price
    ADD CONSTRAINT "priceIdPrimaryKey" PRIMARY KEY (id);


--
-- TOC entry 4678 (class 2606 OID 49195)
-- Name: route routeIdPrimaryKey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT "routeIdPrimaryKey" PRIMARY KEY (id);


--
-- TOC entry 4676 (class 2606 OID 49187)
-- Name: segment segmentIdPrimaryKey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT "segmentIdPrimaryKey" PRIMARY KEY (id);


--
-- TOC entry 4682 (class 2606 OID 49210)
-- Name: ticket ticketIdPrimaryKey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT "ticketIdPrimaryKey" PRIMARY KEY (id);


--
-- TOC entry 4680 (class 2606 OID 49204)
-- Name: traveller travellerIdPrimaryKey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.traveller
    ADD CONSTRAINT "travellerIdPrimaryKey" PRIMARY KEY (id);


--
-- TOC entry 4687 (class 2606 OID 49241)
-- Name: route route_cityAForeignKey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT "route_cityAForeignKey" FOREIGN KEY ("city_A") REFERENCES public.city(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 4688 (class 2606 OID 49246)
-- Name: route route_cityBForeignKey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT "route_cityBForeignKey" FOREIGN KEY ("city_B") REFERENCES public.city(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 4685 (class 2606 OID 49221)
-- Name: segment segment_city1IdForeignKey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT "segment_city1IdForeignKey" FOREIGN KEY (city1) REFERENCES public.city(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 4686 (class 2606 OID 49226)
-- Name: segment segment_city2IdForeignKey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT "segment_city2IdForeignKey" FOREIGN KEY (city2) REFERENCES public.city(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 4689 (class 2606 OID 49231)
-- Name: ticket ticket_routeIdForeignKey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT "ticket_routeIdForeignKey" FOREIGN KEY (route_id) REFERENCES public.route(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 4690 (class 2606 OID 49236)
-- Name: ticket ticket_travellerIdForeignKey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT "ticket_travellerIdForeignKey" FOREIGN KEY (traveller_id) REFERENCES public.traveller(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


-- Completed on 2024-07-18 17:36:44

--
-- PostgreSQL database dump complete
--

