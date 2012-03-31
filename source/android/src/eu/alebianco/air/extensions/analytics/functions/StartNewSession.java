/*
 * Air Native Extension for Google Analytics on iOS and Android
 * 
 * Author: Alessandro Bianco
 * http://alessandrobianco.eu
 *
 * Copyright (c) 2012 Alessandro Bianco
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
 package eu.alebianco.air.extensions.analytics.functions;

import android.content.Context;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import eu.alebianco.air.extensions.analytics.GAContext;
import eu.alebianco.air.extensions.analytics.model.LogLevel;
import eu.alebianco.air.extensions.analytics.utils.GAEUtils;

public class StartNewSession implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		
		if (args == null || args.length < 2) {
			
			GAEUtils.logEvent(context, LogLevel.FATAL, "Invalid arguments number for method '%s'", GAEUtils.getClassName());
			return null;
		}
		
		GoogleAnalyticsTracker tracker = ((GAContext) context).tracker;
		Context appContext = context.getActivity().getApplicationContext();
		
		try {
			
			String account = args[0].getAsString();
			int interval = args[1].getAsInt();
			
			if (interval > 0) {
				tracker.startNewSession(account, interval, appContext);
			} 
			else {
				tracker.startNewSession(account, appContext);
			}
		} 
		catch(Exception e) {
			
			GAEUtils.logEvent(context, LogLevel.FATAL, "%s method failed because: %s", GAEUtils.getClassName(), e.getMessage());
		}
		
		return null;
	}

}
