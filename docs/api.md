# API

### Post `/register`

| 名称       | 类型     |
| -------- | ------ |
| uername  | string |
| password | string |
| `return` | void   |

### Post `/login`

| 名称       | 类型               |
| -------- | ---------------- |
| uername  | string           |
| password | string           |
| role     | STUDENT \| ADMIN |
| `return` | `class Admin`    |

### Get`/course/selectPage`

| 名称       | 类型               |
| -------- | ---------------- |
| pageNum  | number           |
| pageSize | number           |
| course   | `class Course`   |
| `return` | PageInfo<Course> |

## Post `/course/add`

| field    | type           |
| -------- | -------------- |
| course   | `class Course` |
| `return` | `class Result` |

## Put `/course/update`

| field    | type           |
| -------- | -------------- |
| course   | `class Course` |
| `return` | `class Result` |

## Delete`/course/delete/{id}`

## Post `/files/upload`

| field    | type                     |
| -------- | ------------------------ |
| file     | application/octet-stream |
| `return` | `class Result`           |

## Get `/files/download`

| field    | type   |
| -------- | ------ |
| fileName | string |
| `return` | void   |

## Post `/grade/add`

| field    | type           |
| -------- | -------------- |
| grade    | `class Grade`  |
| `return` | `class Result` |

## Put `/grade/update`

| field    | type           |
| -------- | -------------- |
| grade    | `class Grade`  |
| `return` | `class Result` |

## Get `/grade/selectPage`

| field    | type           |
| -------- | -------------- |
| pageNum  | number         |
| pageSize | number         |
| grade    | `class Grade`  |
| `return` | `class Result` |

## Delete`/grade/delete/{id}`

## Post `/student/add`

| field    | type           |
| -------- | -------------- |
| grade    | `class Grade`  |
| `return` | `class Result` |

## Put `/student/update`

| field    | type           |
| -------- | -------------- |
| grade    | `class Grade`  |
| `return` | `class Result` |

## Get `/student/selectPage`

| field    | type           |
| -------- | -------------- |
| pageNum  | number         |
| pageSize | number         |
| grade    | `class Grade`  |
| `return` | `class Result` |

## Delete`/student/delete/{id}`

## Post `/studentCourse/add`

| field    | type           |
| -------- | -------------- |
| grade    | `class Grade`  |
| `return` | `class Result` |

## Get `/studentCourse/selectPage`

| field    | type           |
| -------- | -------------- |
| pageNum  | number         |
| pageSize | number         |
| grade    | `class Grade`  |
| `return` | `class Result` |

## Delete`/studentCourse/delete/{id}`

# Type

## `class` Admin

| field    | type   |
| -------- | ------ |
| id       | number |
| name     | string |
| username | string |
| password | string |
| role     | string |

## `class` Course

| field       | type   |
| ----------- | ------ |
| id          | number |
| name        | string |
| no          | string |
| description | string |
| times       | string |
| teachers    | string |

## `class` Result

| field | type   |
| ----- | ------ |
| code  | string |
| msg   | string |
| data  | object |

## `class` Grade

| field       | type   |
| ----------- | ------ |
| id          | number |
| courseId    | number |
| studentId   | number |
| score       | number |
| comment     | string |
| feedback    | string |
| studentName | string |
| courseName  | string |