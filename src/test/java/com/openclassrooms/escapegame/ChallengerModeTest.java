package com.openclassrooms.escapegame;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.openclassrooms.escapegame.controller.*;
import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.view.ConsoleFactory;
import com.openclassrooms.escapegame.view.IConsole;

public class ChallengerModeTest {
	IConsole console;
	Model model;
	Controller controller;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		
	}

	@Before
	public void setUp() throws Exception
	{
		console = new DummyConsole();
		model = new ChallengerModel();
		controller = new ChallengerController(model);
	}

	@After
	public void tearDown() throws Exception
	{
		console = null;
		model = null;
		controller = null;
	}

	@Test
	public void test()
	{
		return;
		//controller.run();
		
	}

}
