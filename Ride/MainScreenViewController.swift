//
//  ViewController.swift
//  Ride
//
//  Created by Riwaz Poudyal on 1/25/18.
//  Copyright © 2018 KR. All rights reserved.
//

import UIKit

class MainScreenViewController: UIViewController {

    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        let storyBoard : UIStoryboard = UIStoryboard(name: "Main", bundle:nil)
        let nextViewController = storyBoard.instantiateViewController(withIdentifier: "DetailsScreen")
        sleep(1)
        self.present(nextViewController, animated: true, completion: nil)
    }
}
