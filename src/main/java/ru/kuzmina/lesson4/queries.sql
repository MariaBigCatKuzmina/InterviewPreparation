-- Сделать запросы, считающие и выводящие в понятном виде:
--ошибки в расписании (фильмы накладываются друг на друга), 
-- отсортированные по возрастанию времени. 
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», 
-- «время начала», «длительность»;
SELECT m1.title as "фильм 1", s1.session_time as "время начала", m1.duration as "длительность", 
		m2.title as "фильм 2", s2.session_time, m2.duration  
	--	, round(extract(epoch FROM s2."session_time" - s1."session_time")/60),
	--	s1."session_time" + make_interval(mins => m1."duration")
FROM public."Movies" m1
INNER JOIN public."Sessions" s1 ON s1.movie_id = m1.id
INNER JOIN public."Sessions" s2 ON s1.id != s2.id AND s1.session_date = s2.session_date 
			AND s2."session_time" > s1.session_time
			AND s2."session_time" < s1.session_time + make_interval(mins => m1.duration)
INNER JOIN public."Movies" m2 ON m2.id = s2.movie_id
ORDER BY s1.session_time;

--перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
SELECT m1.title as "фильм 1", s1.session_time as "время начала", m1."duration" as "длительность", 
		m2.title as "фильм 2", s2.session_time as "время начала второго фильма", 
		round(extract(epoch FROM s2.session_time - s1.session_time - make_interval(mins => m1.duration))/60) as "длительность перерыва, мин"
FROM public."Movies" m1
INNER JOIN public."Sessions" s1 ON s1.movie_id = m1.id
INNER JOIN public."Sessions" s2 ON s1.id != s2.id AND s1.session_date = s2.session_date 
			AND s2.session_time > s1.session_time
			AND round(extract(epoch FROM s2.session_time - s1.session_time - make_interval(mins => m1.duration))/60) >= 30
INNER JOIN public."Movies" m2 ON m2.id = s2.movie_id
ORDER BY 6 desc;

--список фильмов, для каждого — с указанием общего числа посетителей за все время, 
--среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму 
--(отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
(SELECT mv.title, j2.tickets_count as "общее число посетителей", j1.sessions_count as "количество сеансов",
	j2.tickets_count/j1.sessions_count as "среднее число зрителей за сеанс",
	j2.fees as "общая сумма сборов"
FROM "Movies" mv 
INNER JOIN (
	SELECT m.id as mov_id, count(s.id) as sessions_count 
	FROM "Movies" m
	INNER JOIN "Sessions" s ON m.id = s.movie_id
	GROUP BY mov_id
) as j1 ON j1.mov_id = mv.id
INNER JOIN (
	SELECT m.id as mov_id, count(t.number) as tickets_count, sum(s.ticket_price) as fees
	FROM "Movies" m
	INNER JOIN "Sessions" s ON s.movie_id = m.id
	INNER JOIN "Tickets" t ON s.id = t.session_id
	GROUP BY mov_id
) as j2 ON j2.mov_id = mv.id
ORDER BY 5 DESC)
UNION
(SELECT 'Итого' as title, count(t.number) as "общее число посетителей", (SELECT count(id) FROM "Sessions") as "количество сеансов",
	count(t.number)/(SELECT count(id) FROM "Sessions") as "среднее число зрителей за сеанс",
	sum(s.ticket_price) as "общая сумма сборов" 
FROM "Tickets" t
INNER JOIN "Sessions" s ON s.id = t.session_id
ORDER BY 5 DESC);
--число посетителей и кассовые сборы, сгруппированные по времени начала фильма: 
--с 9 до 15, с 15 до 18, sс 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
SELECT 
	 SUM (CASE WHEN session_time between '09:00:00':: time AND '15:00:00'::time THEN 1 ELSE 0 END) as "посетителей с 9 до 15",
	 SUM (CASE WHEN session_time between '09:00:00':: time AND '15:00:00'::time THEN s.ticket_price ELSE 0 END) as "сборы с 9 до 15",
	 SUM (CASE WHEN session_time between '15:00:01':: time AND '18:00:00'::time THEN 1 ELSE 0 END) as "посетителей с 15 до 18",
	 SUM (CASE WHEN session_time between '15:00:01':: time AND '18:00:00'::time THEN s.ticket_price ELSE 0 END) as "сборы с 15 до 18",
	 SUM (CASE WHEN session_time between '18:00:01':: time AND '21:00:00'::time THEN 1 ELSE 0 END) as "посетителей с 18 до 21",
	 SUM (CASE WHEN session_time between '18:00:01':: time AND '21:00:00'::time THEN s.ticket_price ELSE 0 END) as "сборы с 18 до 21",
	 SUM (CASE WHEN session_time between '21:00:01':: time AND '00:00:00'::time THEN 1 ELSE 0 END) as "посетителей с 21 до 00:00",
	 SUM (CASE WHEN session_time between '21:00:01':: time AND '00:00:00'::time THEN s.ticket_price ELSE 0 END) as "сборы с 21 до 00"
FROM "Tickets" t
INNER JOIN "Sessions" s ON t.session_id = s.id;