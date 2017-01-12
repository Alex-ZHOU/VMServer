/*
 * Copyright 2017 Alex_ZHOU
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package utils;

public class AverageAlgorithm {

	private double MiddleNum = 50;

	public static void main(String[] args) {

		int a[] = new int[5];

		for (int i = 0; i < a.length; i++) {
			a[i] = i + 10;
		}

		System.out.println(new AverageAlgorithm().getAverage(a));
		System.out.println(new AverageAlgorithm().getAverage(12,5,a));
	}

	public void setMiddleNum(double num) {
		MiddleNum = num;
	}

	public double getAverage(double historyAverage,int historyNum,int[] a){
		double histpryAmplitude = (historyAverage-(double)MiddleNum)*(double)historyNum;
		
		double thisAmplitude = (getAverage(a)-(double)MiddleNum)*(double)a.length;
		
		return MiddleNum + ((histpryAmplitude+thisAmplitude) / (double)(historyNum+a.length));
		
	}
	
	public double getAverage(int[] a) {

		double amplitude = 0;

		for (int i = 0; i < a.length; i++) {
			amplitude = ((double)a[i] - MiddleNum) + amplitude;
		}

		return MiddleNum + (double)(amplitude / a.length);
	}

}
