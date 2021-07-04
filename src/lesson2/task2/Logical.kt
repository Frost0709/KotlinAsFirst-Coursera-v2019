@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val str = number.toString()
    return str[0].toInt() + str[1].toInt() == str[2].toInt() + str[3].toInt()
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    return when {
        (x1 == x2 || y1 == y2) -> true
        (abs(x2 - x1) == abs(y2 - y1)) -> true
        else -> false
    }
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {

    // вложенная функция
    fun isLeapYear(): Boolean {
        if (year % 4 == 0) {
            if (year % 100 == 0 && year % 400 != 0) return false
            return true
        }
        return false
    }
    return when (month) {
        9, 4, 6, 11 -> 30
        2 -> if (isLeapYear()) 29 else 28
        else -> 31
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    // if (r2 < r1) return false
    // расстояние между центрами окружностей
    // val dif = sqrt(sqr(x2 - x1) + sqr(y2 - y1))
    // если расстояние между центрами окружности плюс радиус окружности r1
    // (т.е. до края окружности образуемой радиусом r1) меньше или равно радиусу r2, значит окружность образуемая
    // радиусом r1 находится внутри окружности образуемой радиусом r2. Интересно, это кто-то читает?
    // return dif + r1 <= r2
    return sqrt(sqr(x2 - x1) + sqr(y2 - y1)) + r1 <= r2
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    // если фигура, образуемая двумя сторонами кирпича проходит в отверстие образуемое 'r' и 's', значит кирпич пройдёт
    // вопрос в том как сделать это покрасивее
    // лучше убрать наибольшую сторону кирпича из уравнения, не забыть обработать крайние варианты
    val max = maxOf(a, b, c)
    val smallerSides = listOf(a, b, c).filter { it != max }
    // чтоб два раза не вставать инициализируем всё максимальной стороной кирпича
    var v1 = max
    var v2 = max
    //
    if (smallerSides.size == 2) {
        v1 = smallerSides[0]
        v2 = smallerSides[1]
    } else if (smallerSides.size == 1) {
        v1 = smallerSides[0]
    }
    return (v1 <= r && v2 <= s) || (v1 <= s && v2 <= r)
}
