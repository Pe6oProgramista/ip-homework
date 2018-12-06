regExps = {
"exercise_1": /[A-Z][a-z]+/,
"exercise_2": /088[1-9]\d{6,6}/,
"exercise_3": /[^10]+/,
"exercise_4": /^((?!no).)*$/,
"exercise_5": /^((\d{0,3})|(1[0-4]\d{0,2})|1500)$/,
"exercise_6": /class=['"].*['"]/
};
cssSelectors = {
"exercise_1": "item > java.class1",
"exercise_2": ".diffClass",
"exercise_3": "java > tag.class1.class2",
"exercise_4": "#someId + item",
"exercise_5": "tag > java + .class1",
"exercise_6": "#someId item > item > item > item",
"exercise_7": "different > different + different > java + java",
"exercise_8": "#someId"
};
