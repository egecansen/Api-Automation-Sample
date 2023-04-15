Feature: PetStore

  Scenario: Post GPT pet
    * Generate a random GPT pet

  Scenario: Post a new pet
    * Post a new random pet named fluffer

  Scenario: Get pet by Id
    * Get pet by id: {}

  Scenario: Put pet
    * Randomly update the pet named: flapper

  Scenario: Find pet by status
    * Find pet by status: pending

  Scenario: Deleting pet from id
    * Delete pet from id: 9223372036854605407

  Scenario: Create a pet and delete it
    * Post a new random pet named tofu
    * Delete pet from context

  Scenario: Create a random pet, update it and get updated pet by id
    * Post a new random pet named tofu
    * Update pet from id: 9223372036854646429, new name: sakiz, new status: pending

  Scenario: Upload a photo for a pet
    * Upload a photo for id numbered 9223372036854774335 pet, metadata: pic1, file path: src/main/java/files/aee.png

  Scenario: Create a user
    * Create the following user:
      | User Name    | Zen               |
      | First Name   | John              |
      | Last Name    | Doe               |
      | Email        | johndoe@zmail.com |
      | Password     | 12345678          |
      | Phone Number | +105834923483     |
      | userStatus   | 1                 |

  Scenario: Create user
    * Create the following users:
      | User Name   | First Name | Last Name | Email                  | Password   | Phone Number | userStatus |
      | JackTheHold | Jack       | Holder    | jackholder@zmail.com   | 9887654321 | +10325432923 | 2          |
      | Keller      | Kelly      | Seller    | kellyseller@zmail.com  | 2345324143 | +10242342344 | 3          |
      | OkayDan     | Sam        | Daniels   | sammydaniels@zmail.com | 2343242323 | +10426544268 | 4          |

  Scenario: Login with context user
    * Create the following user:
      | User Name    | Zen               |
      | First Name   | John              |
      | Last Name    | Doe               |
      | Email        | Johndoe@zmail.com |
      | Password     | 12345678          |
      | Phone Number | +105834923483     |
      | userStatus   | 1                 |

    * Login with the context user