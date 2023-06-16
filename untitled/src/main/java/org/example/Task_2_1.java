/*Java: знакомство и как пользоваться базовым API (семинары)
Урок 2. Почему вы не можете не использовать API
Формат сдачи: ссылка на подписанный git-проект.

Задание

Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя StringBuilder.
Данные для фильтрации приведены ниже в виде json-строки.
Если значение null, то параметр не должен попадать в запрос.
Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
В итоге должно получиться select * from students where name=Ivanov, country=Russia, city=Moscow

Обучающийся: ШИТОВ Олег Владимирович, "Разработчик Python", поток 4544, будни, утро.  14.06.2023
 */

package org.example;
import java.lang.String;
public class Task_2_1 {
    public static void main(String[] args) {

        String str = "select * from students";
        String filter = "{\"name':\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";
        //String filter = "{\"name':\"null\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"30\"}";
        //String filter = "";

        StringBuilder select = new StringBuilder();
        select.append(str);

        filter = filter.replace("{", "").replace("}", "");
        filter = filter.replace("\"", "").replace("\'", "");

        String[] terms = filter.split(",");
        Boolean first = true;
        for (String term : terms) {
            String[] words = term.split(":");
            if ((words.length > 0) && (words.length%2 == 0)) {
                if (words[1].toLowerCase().equals("null")) {
                } else {
                    String field = words[0].replaceAll("\"", "").replaceAll("'", "").strip();
                    if (first) {
                        first = false;
                        select.append(" where ");
                    } else {
                        select.append(" and ");
                    }
                    select.append(field + "=\"" + words[1] + "\"");
                }
            } else {System.out.println("Некорректные исходные данные");}
        }
        select.append(";");
        System.out.println(select);
    }
}

/*Примеры применения:
select * from students where name="Ivanov" and country="Russia" and city="Moscow";
select * from students where country="Russia" and city="Moscow" and age="30";
select * from students;
 */