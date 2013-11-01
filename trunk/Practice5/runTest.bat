@echo off
java -classpath lib/junit-4.8.2.jar;classes org.junit.runner.JUnitCore ^
ua.edu.sumdu.ta.alexandersudarenko.pr5.tests.TaskTest ^
ua.edu.sumdu.ta.alexandersudarenko.pr5.tests.ArrayTaskListTest ^
ua.edu.sumdu.ta.alexandersudarenko.pr5.tests.LinkedTaskListTest
pause