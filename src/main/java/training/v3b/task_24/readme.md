# 24. Покупка билетов

| Параметр            | Условие                          |
|---------------------|----------------------------------|
| Ограничение времени | 1 секунда                        |
| Ограничение памяти  | 64Mb                             |
| Ввод                | стандартный ввод или input.txt   |
| Вывод               | стандартный вывод или output.txt |


За билетами на премьеру нового мюзикла выстроилась очередь из **N** человек, 
каждый из которых хочет купить 1 билет.   
На всю очередь работала только одна касса, поэтому продажа билетов шла очень медленно, 
приводя «постояльцев» очереди в отчаяние.  
Самые сообразительные быстро заметили, что, как правило, несколько билетов в одни руки кассир продаёт 
быстрее, чем когда эти же билеты продаются по одному. Поэтому они предложили нескольким подряд 
стоящим людям отдавать деньги первому из них, чтобы он купил билеты на всех.   
Однако для борьбы со спекулянтами кассир продавала не более 3-х билетов в одни руки, поэтому договориться таким образом между собой могли лишь 2 или 3 подряд стоящих человека. 
Известно, что на продажу _i_-му человеку из очереди одного билета кассир тратит A<sub>i</sub> секунд, 
на продажу двух билетов — B<sub>i</sub> секунд, трех билетов — C<sub>i</sub> секунд.  
Напишите программу, которая подсчитает минимальное время за которое могли быть обслужены все покупатели.

Обратите внимание, что билеты на группу объединившихся людей всегда покупает первый из них.  
Также никто в целях ускорения не покупает лишних билетов (то нет билетов, которые никому не нужны).

### Формат ввода
На вход программы поступает сначала число **N** — количество покупателей в очереди (1&nbsp;≤&nbsp;N&nbsp;≤&nbsp;5000).  
Далее идет N троек натуральных чисел A<sub>i</sub>, B<sub>i</sub>, C<sub>i</sub>. Каждое из этих чисел не превышает 3600.  
Люди в очереди нумеруются, начиная от кассы.

### Формат вывода
Требуется вывести одно число — минимальное время в секундах, 
за которое могли быть обслужены все покупатели.


### Пример

##### Пример 1
<table>
    <thead>
        <tr>
            <th width="250px" align="left">Ввод</th>
            <th width="250px" align="left">Вывод</th>
        </tr>
    </thead>
    <tr>
        <td>
            5<br>
            5 10 15<br>
            2 10 15<br>
            5 5 5<br>
            20 20 1<br>
            20 1 1
        </td>
        <td>
            12<br><br><br><br><br><br>
        </td>
    </tr>
</table>


<br>

[Назад к списку задач](https://github.com/AlexAkama/yandex_algorithm/tree/main/src/main/java/training/v3b#%D0%B7%D0%B0%D0%B4%D0%B0%D1%87%D0%B8-30)