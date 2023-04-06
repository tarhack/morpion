::
:: Run Java Morpion Game
:: Parameter : Number of cases
::
@echo OFF
set NUM_CASE=%1
if not %NUM_CASE%. == . (
    set NUM_CASE=--number %NUM_CASE%
)
java -cp ./morpion-1.0.jar com.codingf.morpion.Morpion %NUM_CASE%