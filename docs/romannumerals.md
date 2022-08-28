# The Roman Numerals Kata

## Situation

You're doing an internship in the film industry and your department is responsible for making the meta-information of
movies indexable and readable for search engines. Unfortunately, the production dates of the movies are only given in
Roman numerals. E.g. MMXXII (meaning 2022). To make the movies searchable and filterable by their production date, your
first assignment is to convert these Roman numerals to Arabic decimals. As this has to be done for thousands of movies,
your task is to automate this tedious work by writing code providing this functionality.

## Task

Your task is to develop an API that converts Roman numerals to Arabic decimals. The mapping is the following:

| Arabic number | Roman numeral |
|---------------|---------------|
| 1             | I             |
| 5             | V             |
| 10            | X             |
| 50            | L             |
| 100           | C             |
| 500           | D             |
| 1000          | M             |

More detailed instructions how to combine the numerals can be found
on [Wikipedia](https://en.wikipedia.org/wiki/Roman_numerals)

### Input

The input to the program is a string of Roman numerals, e.g. MMXXII

### Output

The program's output is the Arabic decimal representation of the Roman numeral, e.g. 2022

### Examples

| Arabic number | Roman numeral |
|---------------|---------------|
| 0             |               |
| 4             | IV            |
| 9             | IX            |
| 29            | XXIX          |
| 80            | LXXX          |
| 294           | CCXCIV        |
| 2019          | MMXIX         |

## Additional Task

Do it the other way round: Convert Arabic decimals to Roman numerals.

