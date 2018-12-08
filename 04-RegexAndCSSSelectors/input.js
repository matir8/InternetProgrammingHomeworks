regExps = {
"exercise_1": /([A-Z][a-z]+)/,
"exercise_2": /088[1-7]{7}/,
"exercise_3": /[^0-1]+/,
"exercise_4": /^[^\.\d\_][A-Za-z\.\_\d]{2,29}$/,
"exercise_5": /^([1-9]|[1-9][0-9]?|[1-9][0-9][0-9]?|(1[0-4][0-9][0-9]?)|1500)$/,
"exercise_6": /class=(\"|\').*(\"|\')/
};
cssSelectors = {
"exercise_1": "item > java",
"exercise_2": "#diffId *, #diffId2 *",
"exercise_3": "java.class1 .class1.class2",
"exercise_4": "item:nth-first-child(3)",
"exercise_5": "item tag java:nth-last-child(2)",
"exercise_6": "#someId item item:first-child .class2 .class1, #someId item item:nth-last-child(1) item:nth-last-child(1)",
"exercise_7": "#diffId2 java:last-child",
"exercise_8": "#someId"
};
