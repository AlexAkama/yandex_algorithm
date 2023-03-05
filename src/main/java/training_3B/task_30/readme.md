# 30. НОП с восстановлением ответа

| Параметр            | Условие                          |
|---------------------|----------------------------------|
| Ограничение времени | 1 секунда                        |
| Ограничение памяти  | 64Mb                             |
| Ввод                | стандартный ввод или input.txt   |
| Вывод               | стандартный вывод или output.txt |

Даны две последовательности, требуется найти и вывести их наибольшую общую подпоследовательность.

### Формат ввода
В первой строке входных данных содержится число **N** – длина первой последовательности (1&nbsp;≤&nbsp;N&nbsp;≤&nbsp;1000).  
Во второй строке заданы члены первой последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.
В третьей строке записано число **M** – длина второй последовательности (1 ≤ M ≤ 1000).  
В четвертой строке задаются члены второй последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.

### Формат вывода
Требуется вывести наибольшую общую подпоследовательность данных последовательностей, через пробел.

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
            3<br>
            1 2 3<br>
            3<br>
            2 3 1
        </td>
        <td>
            2 3<br><br><br><br>
        </td>
    </tr>
</table>

##### Пример 2
<table>
    <thead>
        <tr>
            <th width="250px" align="left">Ввод</th>
            <th width="250px" align="left">Вывод</th>
        </tr>
    </thead>
    <tr>
        <td>
            3<br>
            1 2 3<br>
            3<br>
            3 2 1
        </td>
        <td>
            1<br><br><br><br>
        </td>
    </tr>
</table>