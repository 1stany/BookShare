--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.2

-- Started on 2024-06-12 10:22:42 UTC

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

--
-- TOC entry 216 (class 1259 OID 41399)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_id_seq OWNER TO "postgresMaster";

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 41391)
-- Name: app_user; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.app_user (
    id bigint DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    firstname character varying(32) NOT NULL,
    lastname character varying(32) NOT NULL,
    email character varying(32) NOT NULL,
    phone character varying(32),
    gender character varying(16),
    birthdate date NOT NULL,
    description character varying(256),
    city_id bigint NOT NULL,
    CONSTRAINT check_maggiorenni CHECK ((age((birthdate)::timestamp with time zone) >= '18 years'::interval)),
    CONSTRAINT check_mail_format CHECK (((email)::text ~* '^[A-Za-z0-9.%+-]+@[A-Za-z0-9.-]+.[A-Z|a-z]{2,}$'::text)),
    CONSTRAINT gender_check CHECK (((gender)::text = ANY ((ARRAY['male'::character varying, 'female'::character varying, 'prefer not say'::character varying])::text[])))
);


ALTER TABLE public.app_user OWNER TO "postgresMaster";

--
-- TOC entry 230 (class 1259 OID 49824)
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.category_id_seq OWNER TO "postgresMaster";

--
-- TOC entry 226 (class 1259 OID 49741)
-- Name: category; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.category (
    id bigint DEFAULT nextval('public.category_id_seq'::regclass) NOT NULL,
    name character varying(32) NOT NULL,
    description character varying(128)
);


ALTER TABLE public.category OWNER TO "postgresMaster";

--
-- TOC entry 217 (class 1259 OID 41403)
-- Name: city; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.city (
    id bigint NOT NULL,
    name character varying(32) NOT NULL,
    lockerpoint character varying(32) NOT NULL
);


ALTER TABLE public.city OWNER TO "postgresMaster";

--
-- TOC entry 218 (class 1259 OID 41410)
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.city_id_seq OWNER TO "postgresMaster";

--
-- TOC entry 3437 (class 0 OID 0)
-- Dependencies: 218
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;


--
-- TOC entry 222 (class 1259 OID 49713)
-- Name: general_list_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.general_list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.general_list_id_seq OWNER TO "postgresMaster";

--
-- TOC entry 220 (class 1259 OID 49595)
-- Name: object_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.object_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.object_id_seq OWNER TO "postgresMaster";

--
-- TOC entry 219 (class 1259 OID 41421)
-- Name: object; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.object (
    id bigint DEFAULT nextval('public.object_id_seq'::regclass) NOT NULL,
    name character varying(32) NOT NULL,
    description character varying(256) NOT NULL,
    activetrade boolean DEFAULT true NOT NULL,
    condition character varying(32) NOT NULL,
    condition_comment character varying(128),
    category_id bigint NOT NULL,
    owner_id bigint NOT NULL,
    creation_date date DEFAULT now() NOT NULL,
    CONSTRAINT check_condition CHECK (((condition)::text = ANY ((ARRAY['come nuovo'::character varying, 'ottimo'::character varying, 'buono'::character varying, 'accettabile'::character varying])::text[])))
);


ALTER TABLE public.object OWNER TO "postgresMaster";

--
-- TOC entry 224 (class 1259 OID 49717)
-- Name: object_trade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.object_trade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.object_trade_id_seq OWNER TO "postgresMaster";

--
-- TOC entry 227 (class 1259 OID 49751)
-- Name: object_trade; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.object_trade (
    id bigint DEFAULT nextval('public.object_trade_id_seq'::regclass) NOT NULL,
    requested_object_id bigint NOT NULL,
    requesting_user_id bigint NOT NULL,
    exchanged_object_id bigint,
    request_date date DEFAULT now() NOT NULL,
    accepted boolean DEFAULT false NOT NULL,
    exchange_date date NOT NULL,
    home_user_id bigint
);


ALTER TABLE public.object_trade OWNER TO "postgresMaster";

--
-- TOC entry 223 (class 1259 OID 49715)
-- Name: object_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.object_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.object_type_id_seq OWNER TO "postgresMaster";

--
-- TOC entry 228 (class 1259 OID 49782)
-- Name: object_type; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.object_type (
    id bigint DEFAULT nextval('public.object_type_id_seq'::regclass) NOT NULL,
    name character varying(32) NOT NULL,
    description character varying(256),
    category_id bigint NOT NULL,
    date_added date DEFAULT now() NOT NULL,
    wishing_user_id bigint NOT NULL
);


ALTER TABLE public.object_type OWNER TO "postgresMaster";

--
-- TOC entry 231 (class 1259 OID 49828)
-- Name: offer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.offer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.offer_id_seq OWNER TO "postgresMaster";

--
-- TOC entry 229 (class 1259 OID 49797)
-- Name: offer; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.offer (
    id bigint DEFAULT nextval('public.offer_id_seq'::regclass) NOT NULL,
    offered_object_id bigint NOT NULL,
    user_id bigint NOT NULL,
    description character varying(256) NOT NULL,
    object_type_id bigint,
    active boolean DEFAULT false NOT NULL
);


ALTER TABLE public.offer OWNER TO "postgresMaster";

--
-- TOC entry 225 (class 1259 OID 49719)
-- Name: review_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.review_id_seq OWNER TO "postgresMaster";

--
-- TOC entry 221 (class 1259 OID 49671)
-- Name: review; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.review (
    id bigint DEFAULT nextval('public.review_id_seq'::regclass) NOT NULL,
    title character varying(64) NOT NULL,
    content character varying(256),
    rating integer NOT NULL,
    object_trade_id bigint NOT NULL,
    app_user_id bigint NOT NULL,
    CONSTRAINT check_rating_range CHECK (((rating >= 1) AND (rating <= 5)))
);


ALTER TABLE public.review OWNER TO "postgresMaster";

--
-- TOC entry 3240 (class 2604 OID 49822)
-- Name: city id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.city ALTER COLUMN id SET DEFAULT nextval('public.city_id_seq'::regclass);


--
-- TOC entry 3268 (class 2606 OID 49745)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 3260 (class 2606 OID 41407)
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- TOC entry 3264 (class 2606 OID 41425)
-- Name: object object_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object
    ADD CONSTRAINT object_pkey PRIMARY KEY (id);


--
-- TOC entry 3270 (class 2606 OID 49756)
-- Name: object_trade object_trade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object_trade
    ADD CONSTRAINT object_trade_pkey PRIMARY KEY (id);


--
-- TOC entry 3272 (class 2606 OID 49786)
-- Name: object_type object_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object_type
    ADD CONSTRAINT object_type_pkey PRIMARY KEY (id);


--
-- TOC entry 3274 (class 2606 OID 49801)
-- Name: offer offer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.offer
    ADD CONSTRAINT offer_pkey PRIMARY KEY (id);


--
-- TOC entry 3266 (class 2606 OID 49675)
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- TOC entry 3262 (class 2606 OID 41420)
-- Name: city unico_id; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT unico_id UNIQUE (id);


--
-- TOC entry 3258 (class 2606 OID 41395)
-- Name: app_user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 3276 (class 2606 OID 49834)
-- Name: object object_app_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object
    ADD CONSTRAINT object_app_user_fk FOREIGN KEY (owner_id) REFERENCES public.app_user(id) NOT VALID;


--
-- TOC entry 3277 (class 2606 OID 49746)
-- Name: object object_category_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object
    ADD CONSTRAINT object_category_fk FOREIGN KEY (category_id) REFERENCES public.category(id) NOT VALID;


--
-- TOC entry 3280 (class 2606 OID 49762)
-- Name: object_trade object_trade_exchanged_object_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object_trade
    ADD CONSTRAINT object_trade_exchanged_object_fk FOREIGN KEY (exchanged_object_id) REFERENCES public.object(id);


--
-- TOC entry 3281 (class 2606 OID 49772)
-- Name: object_trade object_trade_home_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object_trade
    ADD CONSTRAINT object_trade_home_user_fk FOREIGN KEY (home_user_id) REFERENCES public.app_user(id);


--
-- TOC entry 3282 (class 2606 OID 49757)
-- Name: object_trade object_trade_requested_object_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object_trade
    ADD CONSTRAINT object_trade_requested_object_fk FOREIGN KEY (requested_object_id) REFERENCES public.object(id);


--
-- TOC entry 3283 (class 2606 OID 49767)
-- Name: object_trade object_trade_requesting_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object_trade
    ADD CONSTRAINT object_trade_requesting_user_fk FOREIGN KEY (requesting_user_id) REFERENCES public.app_user(id);


--
-- TOC entry 3284 (class 2606 OID 49787)
-- Name: object_type object_type_category_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object_type
    ADD CONSTRAINT object_type_category_fk FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 3285 (class 2606 OID 49792)
-- Name: object_type object_type_wishing_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.object_type
    ADD CONSTRAINT object_type_wishing_user_fk FOREIGN KEY (wishing_user_id) REFERENCES public.app_user(id);


--
-- TOC entry 3286 (class 2606 OID 49812)
-- Name: offer offer_object_type_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.offer
    ADD CONSTRAINT offer_object_type_fk FOREIGN KEY (object_type_id) REFERENCES public.object_type(id);


--
-- TOC entry 3287 (class 2606 OID 49802)
-- Name: offer offer_offered_object_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.offer
    ADD CONSTRAINT offer_offered_object_fk FOREIGN KEY (offered_object_id) REFERENCES public.object(id);


--
-- TOC entry 3288 (class 2606 OID 49807)
-- Name: offer offer_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.offer
    ADD CONSTRAINT offer_user_fk FOREIGN KEY (user_id) REFERENCES public.app_user(id);


--
-- TOC entry 3278 (class 2606 OID 49681)
-- Name: review review_app_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_app_user_fk FOREIGN KEY (app_user_id) REFERENCES public.app_user(id) NOT VALID;


--
-- TOC entry 3279 (class 2606 OID 49777)
-- Name: review review_object_trade_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_object_trade_fk FOREIGN KEY (object_trade_id) REFERENCES public.object_trade(id) NOT VALID;


--
-- TOC entry 3275 (class 2606 OID 49606)
-- Name: app_user user_city_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT user_city_fk FOREIGN KEY (city_id) REFERENCES public.city(id) NOT VALID;


-- Completed on 2024-06-12 10:22:42 UTC

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.2

-- Started on 2024-06-12 10:22:58 UTC

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

--
-- TOC entry 3416 (class 0 OID 41403)
-- Dependencies: 217
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

INSERT INTO public.city VALUES (1, 'Roma', 'Piazza Spagna');


--
-- TOC entry 3414 (class 0 OID 41391)
-- Dependencies: 215
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

INSERT INTO public.app_user VALUES (1, 'Franco', 'Armani', 'franco.ar@gmail.com', '332243546', 'male', '1970-04-02', 'Sono una persona solare e mi piace molto il bricolage', 1);
INSERT INTO public.app_user VALUES (2, 'Amelia', 'Ginori', 'gino@libero.it', '333987654', 'female', '1954-11-03', 'Amo il giardinaggio, sono un''esperta di fiori e pianti. Voglio coltivare il mio paradiso verde', 1);
INSERT INTO public.app_user VALUES (3, 'Pasquale', 'Pratticò', 'pratty@yahoo.it', '331123456', 'prefer not say', '1990-12-25', 'Cerco tutto ciò che riguarda la tecnologia, anche vintage.', 1);


--
-- TOC entry 3425 (class 0 OID 49741)
-- Dependencies: 226
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

INSERT INTO public.category VALUES (1, 'elettronica', 'dispositivi di ultima generazione, accessori tecnologici, audio e video, fotografia, console e videogiochi');
INSERT INTO public.category VALUES (2, 'giardinaggio', 'strumenti e accessori per coltivare piante, fiori e ortaggi. Tutto il necessario per il tuo giardino.');


--
-- TOC entry 3418 (class 0 OID 41421)
-- Dependencies: 219
-- Data for Name: object; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

INSERT INTO public.object VALUES (2, 'tagliaerba', 'potente tagliareba a benzina, design ergonomico', true, 'ottimo', 'ha qualche graffio ma è stato usato pochissimo', 2, 1);
INSERT INTO public.object VALUES (1, 'mouse', 'mouse senza filo usb', true, 'accettabile', 'funziona ancora nonostante gli anni di uso. La rotella va sostituita.', 1, 2);
INSERT INTO public.object VALUES (3, 'pc', 'laptop ASUS grigio, RAM 8GB, intel i5, SSD 150GB', false, 'buono', 'è un po'' lento, si surriscalda ma funziona per le cose basilari', 1, 2);
INSERT INTO public.object VALUES (4, 'stampante', 'stampa solo in bianco e nero, c''è da cabiare il toner', true, 'come nuovo', 'ho finito il primo toner e non l''ho più usata', 1, 2);
INSERT INTO public.object VALUES (5, 'forbici per potare', 'forbici robuste con lame in acciaio temperato', false, 'come nuovo', 'me le hanno regalate per il compleanno, non le ho mai usate perché non ho un giardino.', 2, 3);


--
-- TOC entry 3426 (class 0 OID 49751)
-- Dependencies: 227
-- Data for Name: object_trade; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

INSERT INTO public.object_trade VALUES (1, 2, 2, 1, '2024-06-02', true, '2024-06-22', 1);
INSERT INTO public.object_trade VALUES (2, 3, 3, 5, '2024-05-20', true, '2024-06-10', 3);


--
-- TOC entry 3427 (class 0 OID 49782)
-- Dependencies: 228
-- Data for Name: object_type; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

INSERT INTO public.object_type VALUES (1, 'stampante', 'che stampi solo in bianco e nero', 1, '2024-06-02', 1);


--
-- TOC entry 3428 (class 0 OID 49797)
-- Dependencies: 229
-- Data for Name: offer; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

INSERT INTO public.offer VALUES (1, 4, 2, 'stampante in bianco e nero come nuova', 1, true);


--
-- TOC entry 3420 (class 0 OID 49671)
-- Dependencies: 221
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

INSERT INTO public.review VALUES (1, 'Emozionante!', 'Ho scambiato il mio vecchio PC ASUS con una forbice da potatura usata e sono soddisfatto! La forbice è perfetta per le mie esigenze di giardinaggio. Pasquale è stato molto carino! Un ottimo affare!', 5, 2, 2);
INSERT INTO public.review VALUES (2, 'Ci voleva', 'Ho scambiato la mia forbice da potatura con un PC ASUS usato e sono soddisfatto! Il PC funziona bene per navigazione e compiti leggeri. Un ottimo affare per qualcosa che non ho mai usato!', 5, 2, 3);


--
-- TOC entry 3436 (class 0 OID 0)
-- Dependencies: 230
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.category_id_seq', 2, true);


--
-- TOC entry 3437 (class 0 OID 0)
-- Dependencies: 218
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.city_id_seq', 1, true);


--
-- TOC entry 3438 (class 0 OID 0)
-- Dependencies: 222
-- Name: general_list_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.general_list_id_seq', 1, false);


--
-- TOC entry 3439 (class 0 OID 0)
-- Dependencies: 220
-- Name: object_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.object_id_seq', 5, true);


--
-- TOC entry 3440 (class 0 OID 0)
-- Dependencies: 224
-- Name: object_trade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.object_trade_id_seq', 2, true);


--
-- TOC entry 3441 (class 0 OID 0)
-- Dependencies: 223
-- Name: object_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.object_type_id_seq', 1, true);


--
-- TOC entry 3442 (class 0 OID 0)
-- Dependencies: 231
-- Name: offer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.offer_id_seq', 1, true);


--
-- TOC entry 3443 (class 0 OID 0)
-- Dependencies: 225
-- Name: review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.review_id_seq', 2, true);


--
-- TOC entry 3444 (class 0 OID 0)
-- Dependencies: 216
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.user_id_seq', 3, true);


-- Completed on 2024-06-12 10:22:59 UTC

--
-- PostgreSQL database dump complete
--
