regExps = {
"exercise_1": /[A-Z][a-z]+/,
"exercise_2": /088[1-9]\d{6}/,
"exercise_3": /[^10]+/,
"exercise_4": /^[a-zA-Z][a-zA-Z_.0-9]{2,29}$/,
"exercise_5": /^(\d{0,3}|1[0-4]\d{2}|1500)$/,
"exercise_6": /class=('.*'|".*")/
};
cssSelectors = {
"exercise_1": "item > java.class1",
"exercise_2": ".diffClass",
"exercise_3": "java > tag.class1.class2",
"exercise_4": "#someId + item",
"exercise_5": "tag > java + .class1",
"exercise_6": "#someId item > item > item > item",
"exercise_7": "#diffId2 > java + java",
"exercise_8": "#someId"
};
