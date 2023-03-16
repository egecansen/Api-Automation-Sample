Feature: PetStore Tests

  Scenario: Get pet by Id
    * Get pet by id: 9223372036854663036


  Scenario: Post a new pet
    * Post a new random pet named zembil


  Scenario: Put pet
    * Randomly update the pet named: lale


  Scenario: Find pet by status
    * Find pet by status: pending


  Scenario: Deleting pet from id
    * Delete pet from id: 9223372036854605407


  Scenario: Create a pet and delete it
    * Post a new random pet named lale
    * Delete pet from context


  Scenario: Create a random pet, update it and get updated pet by id
    * Post a new random pet named lale
    * Update pet from id: 9223372036854646429, new name: lala, new status: pending


  Scenario: Upload a photo for a pet
    * Upload a photo for id numbered 9223372036854663036 pet, metadata: best, file url: src/main/java/files/aee.png


  Scenario: Create a user
    * Create the following user:
      | User Name    | Zon               |
      | First Name   | John              |
      | Last Name    | Doe               |
      | Email        | Johndoe@zmail.com |
      | Password     | 12345678          |
      | Phone Number | +105834923483     |
      | userStatus   | 1                 |


  Scenario: Create user
    * Create the following users:
      | User Name   | First Name | Last Name | Email                  | Password   | Phone Number | userStatus |
      | JackTheHold | Jack       | Holder    | jackholder@zmail.com   | 9887654321 | +10325432923 | 2          |
      | Keller      | Kelly      | Seller    | kellyseller@zmail.com  | 2345324143 | +10242342344 | 3          |
      | OkayDan     | Dan        | Daniels   | dannydaniels@zmail.com | 2343242323 | +10426544268 | 4          |

  Scenario: Login with context user
    * Create the following user:
      | User Name    | Zon               |
      | First Name   | John              |
      | Last Name    | Doe               |
      | Email        | Johndoe@zmail.com |
      | Password     | 12345678          |
      | Phone Number | +105834923483     |
      | userStatus   | 1                 |

    * Login with the context user