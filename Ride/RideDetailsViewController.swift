//
//  RideDetailsViewController.swift
//  Ride
//
//  Created by Riwaz Poudyal on 1/30/18.
//  Copyright © 2018 KR. All rights reserved.
//

import UIKit

class RideDetailsViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var fromDestination: UIPickerView!
    @IBOutlet weak var toDestination: UIPickerView!
    
    var fromDestinationData: [String] = ["Prospect", "Morgan", "Williamstown", "Albany"]
    var toDestinationData: [String] = ["Williamstown", "Albany", "New York", "Pittsfield"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        fromDestination.dataSource = self
        toDestination.dataSource = self
        fromDestination.delegate = self
        toDestination.delegate = self
        
        // The number of columns of data
        func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
            return 1
        }
        
        // The number of rows of data
        func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
            if pickerView == fromDestination {
                return fromDestinationData.count
            } else {
                return toDestinationData.count
            }
        }
        
        // The data to return for the row and component (column) that's being passed in
        func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
            if pickerView == fromDestination {
                return fromDestinationData[row]
            } else {
                return toDestinationData[row]
            }
        }
    }
    
}
