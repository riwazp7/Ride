//
//  ViewController.swift
//  Ride
//
//  Created by Riwaz Poudyal on 1/25/18.
//  Copyright © 2018 KR. All rights reserved.
//

import UIKit

class MainScreenViewController: UIViewController {

    @IBOutlet weak var requestButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        requestButton.layer.cornerRadius = 8
    }

    override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
    }
}
