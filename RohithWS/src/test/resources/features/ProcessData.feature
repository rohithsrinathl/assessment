@API
Feature: Read,Process and Generate Output
 
Scenario: ReadInputAndGenerateOutput 
   Given the data from Input files "InstrumentDetails.csv" and "PositionDetails.csv" are read from location "app\\in"
	 When the data from the files are processed
	 Then the processed data and ouput file "PositionReport.csv" is generated in the location "app\\out"



