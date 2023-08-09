package com.assess.definitions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProcessDataStepDef {

	private static final String CSV_FILE_PATH = "C:\\Users\\Rohith\\Desktop\\RohithWS";

	List<String[]> instrumentData = new ArrayList<String[]>();
	List<String[]> positionData = new ArrayList<String[]>();
	List<String[]> outputData = new ArrayList<String[]>();

	@Given("the data from Input files {string} and {string} are read from location {string}")
	public void the_data_from_input_files_and_are_read_from_location(String instrumentFile, String positionFile,
			String location) {
		try {
			FileReader firstFilereader = new FileReader(CSV_FILE_PATH + "\\" + location + "\\" + instrumentFile);
			CSVReader csvReader = new CSVReaderBuilder(firstFilereader).withSkipLines(1).build();
			instrumentData = csvReader.readAll();

			FileReader secondFilereader = new FileReader(CSV_FILE_PATH + "\\" + location + "\\" + positionFile);
			csvReader = new CSVReaderBuilder(secondFilereader).withSkipLines(1).build();
			positionData = csvReader.readAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("the data from the files are processed")
	public void the_data_from_the_files_are_processed() {
		for (String[] instrumentrow : instrumentData) {
			int id = Integer.parseInt(instrumentrow[0]);
			for (String[] positionRow : positionData) {
				int idInPosition = Integer.parseInt(positionRow[1]);
				if (id == idInPosition) {
					int quantity = Integer.parseInt(positionRow[2]);
					int price = Integer.parseInt(instrumentrow[3]);
					int TotalQuantity = quantity * price;
					System.out.print(id + "\t" + positionRow[0] + "\t" + instrumentrow[2] + "\t" + positionRow[2] + "\t"
							+ TotalQuantity);
					String[] outputRow = { Integer.toString(id), positionRow[0], instrumentrow[2], positionRow[2],
							Integer.toString(TotalQuantity) };
					outputData.add(outputRow);
				}
			}
		}

	}

	@Then("the processed data and ouput file {string} is generated in the location {string}")
	public void the_processed_data_and_ouput_file_is_generated_in_the_location(String outputFile, String location) {
		File file = new File(CSV_FILE_PATH + "\\" + location + "\\" + outputFile);
		try {
			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);
			String[] header = { "ID", "PositionID", "ISIN", "Quantity", "Total Price" };
			writer.writeNext(header);
			for (String[] outputRow : outputData) {
				writer.writeNext(outputRow);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
