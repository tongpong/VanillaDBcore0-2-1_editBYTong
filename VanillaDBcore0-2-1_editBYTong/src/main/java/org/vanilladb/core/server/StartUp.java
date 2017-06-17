/*******************************************************************************
 * Copyright 2016 vanilladb.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.vanilladb.core.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.vanilladb.core.remote.jdbc.JdbcStartUp;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class StartUp {
	private static Logger logger = Logger.getLogger(StartUp.class.getName());

	public static void main(String args[]) throws Exception {
		if (logger.isLoggable(Level.INFO))
			logger.info("initing...");

		// configure and initialize the database
		VanillaDb.init(args[0]);
		FileWriter fw= new FileWriter ("VANILLADBSTARTUP_LOG",true);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		String log_out = args[0]+" "+strDate+"\n";
		fw.write(log_out);
		fw.close();	
		// start up the listening port
		JdbcStartUp.startUp(1099);

		if (logger.isLoggable(Level.INFO))
			logger.info("database server ready");
	}
}
