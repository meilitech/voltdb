package vmcTest.tests

import org.junit.Test
import vmcTest.pages.*
import geb.Page.*


class AdminExportTest extends TestBase {
    int insideCount = 0
    boolean loopStatus = false

    def setup() { // called before each test
        int count = 0

        while(count<numberOfTrials) {
            count ++
            try {
                setup: 'Open VMC page'
                to VoltDBManagementCenterPage
                expect: 'to be on VMC page'
                at VoltDBManagementCenterPage

                when: 'click the Admin link (if needed)'
                page.openAdminPage()
                then: 'should be on Admin page'
                at AdminPage

                break
            } catch (org.openqa.selenium.ElementNotVisibleException e) {
                println("ElementNotVisibleException: Unable to Start the test")
                println("Retrying")
            }
        }
    }

    def "Check export Click and check its value"() {
        when:
        waitFor(waitTime) { page.exportbtn.isDisplayed() }
        page.exportbtn.click()
        then:
        waitFor(waitTime) { page.noconfigtxt.isDisplayed() }
        if(page.noconfigtxt.text()=="No configuration available.") {
            println("Currently, No configurations are available in export")
        } else {
            println("Early presence of Configuration settings detected!")
        }
        page.exportbtn.click()
    }

//    def "Verify Export Tables button and texts" () {
//        when: 'Export and AddConfig is displayed'
//        waitFor(waitTime) { page.export.isDisplayed() }
//        waitFor(waitTime) { page.addconfig.isDisplayed() }
//        then: 'Expand Export'
//        page.export.click()
//        waitFor(waitTime) { page.overview.exportTablesText.isDisplayed() }
//        waitFor(waitTime) { page.overview.listOfExport.isDisplayed() }
//    }
//
//    def "Verify Add Configuration opens/Verify Add Configuration for FILE"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("FILE")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.type.value().equals("type") }
//        waitFor(waitTime) { page.overview.nonce.value().equals("nonce") }
//        waitFor(waitTime) { page.overview.outdir.value().equals("outdir") }
//        waitFor(waitTime) { page.overview.typeValue.isDisplayed() }
//        waitFor(waitTime) { page.overview.nonceValue.isDisplayed() }
//        waitFor(waitTime) { page.overview.outdirValue.isDisplayed() }
//    }
//
//    def "Verify Add Configuration for JDBC"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("JDBC")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.jdbcdriver.value().equals("jdbcdriver") }
//        waitFor(waitTime) { page.overview.jdbcurl.value().equals("jdbcurl") }
//        waitFor(waitTime) { page.overview.jdbcdriverValue.isDisplayed() }
//        waitFor(waitTime) { page.overview.jdbcurlValue.isDisplayed() }
//    }
//
//    def "Verify Add Configuration for KAFKA"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("KAFKA")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.metadatabroker.value().equals("metadata.broker.list") }
//        waitFor(waitTime) { page.overview.metadatabrokerValue.isDisplayed() }
//    }
//
//    def "Verify Add Configuration for HTTP"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("HTTP")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.endpoint.value().equals("endpoint") }
//        waitFor(waitTime) { page.overview.endpointValue.isDisplayed() }
//    }
//
//    def "Verify Add Configuration for RABBITMQ/Verify Add Configuration for RABBITMQ broker.host"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("RABBITMQ")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.rabbitMq.value().equals("broker.host") }
//        waitFor(waitTime) { page.overview.rabbitMqValue.isDisplayed() }
//    }
//
//    def "Verify Add Configuration for RABBITMQ amqp.uri"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("RABBITMQ")
//        page.overview.rabbitMq.value("amqp.uri")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.rabbitMq.value().equals("amqp.uri") }
//        waitFor(waitTime) { page.overview.rabbitMqValue.isDisplayed() }
//    }
//
//    def "Verify Add Configuration for CUSTOM"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("CUSTOM")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.exportConnectorClass.isDisplayed() }
//    }
//
//    def "Verify Add Configuration for ELASTICSEARCH"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("ELASTICSEARCH")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.endpointES.value().equals("endpoint") }
//        waitFor(waitTime) { page.overview.endpointESValue.isDisplayed() }
//    }
//
//    def "Verify Error messages of Add Configuration for FILE"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("FILE")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.type.value().equals("type") }
//        waitFor(waitTime) { page.overview.nonce.value().equals("nonce") }
//        waitFor(waitTime) { page.overview.outdir.value().equals("outdir") }
//        waitFor(waitTime) { page.overview.typeValue.isDisplayed() }
//        waitFor(waitTime) { page.overview.nonceValue.isDisplayed() }
//        waitFor(waitTime) { page.overview.outdirValue.isDisplayed() }
//
//        when: 'Save button is clicked'
//        page.overview.save.click()
//        then: 'Error messages are displayed'
//        waitFor(waitTime) { page.overview.errorStream.isDisplayed() }
//        waitFor(waitTime) { page.overview.errorFileTypeValue.isDisplayed() }
//        waitFor(waitTime) { page.overview.errornonceValue.isDisplayed() }
//        waitFor(waitTime) { page.overview.errorOutdirValue.isDisplayed() }
//    }
//
//    def "Verify Error messages of Add Configuration for JDBC"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("JDBC")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.jdbcdriver.value().equals("jdbcdriver") }
//        waitFor(waitTime) { page.overview.jdbcurl.value().equals("jdbcurl") }
//        waitFor(waitTime) { page.overview.jdbcdriverValue.isDisplayed() }
//        waitFor(waitTime) { page.overview.jdbcurlValue.isDisplayed() }
//
//        when: 'Save button is clicked'
//        page.overview.save.click()
//        then: 'Error messages are displayed'
//        waitFor(waitTime) { page.overview.errorStream.isDisplayed() }
//        waitFor(waitTime) { page.overview.errorJdbcDriverValue.isDisplayed() }
//        waitFor(waitTime) { page.overview.errorJdbcUrlValue.isDisplayed() }
//    }
//
//    def "Verify Error messages of Add Configuration for KAFKA"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("KAFKA")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.metadatabroker.value().equals("metadata.broker.list") }
//        waitFor(waitTime) { page.overview.metadatabrokerValue.isDisplayed() }
//
//        when: 'Save button is clicked'
//        page.overview.save.click()
//        then: 'Error messages are displayed'
//        waitFor(waitTime) { page.overview.errorStream.isDisplayed() }
//        waitFor(waitTime) { page.overview.errorMetadataBrokerListValue.isDisplayed() }
//    }
//
//    def "Verify Error messages of Add Configuration for HTTP"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("HTTP")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.endpoint.value().equals("endpoint") }
//        waitFor(waitTime) { page.overview.endpointValue.isDisplayed() }
//
//        when: 'Save button is clicked'
//        page.overview.save.click()
//        then: 'Error messages are displayed'
//        waitFor(waitTime) { page.overview.errorStream.isDisplayed() }
//        waitFor(waitTime) { page.overview.errorEndpointValue.isDisplayed() }
//    }
//
//    def "Verify Error messages of Add Configuration for RABBITMQ/Verify Error messages of Add Configuration for RABBITMQ broker.host"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("RABBITMQ")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.rabbitMq.value().equals("broker.host") }
//        waitFor(waitTime) { page.overview.rabbitMqValue.isDisplayed() }
//
//        when: 'Save button is clicked'
//        page.overview.save.click()
//        then: 'Error messages are displayed'
//        waitFor(waitTime) { page.overview.errorStream.isDisplayed() }
//        waitFor(waitTime) { page.overview.errorRabbitMqValue.isDisplayed() }
//    }
//
//    def "Verify Error messages of Add Configuration for RABBITMQ amqp.uri"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("RABBITMQ")
//        page.overview.rabbitMq.value("amqp.uri")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.rabbitMq.value().equals("amqp.uri") }
//        waitFor(waitTime) { page.overview.rabbitMqValue.isDisplayed() }
//
//        when: 'Save button is clicked'
//        page.overview.save.click()
//        then: 'Error messages are displayed'
//        waitFor(waitTime) { page.overview.errorStream.isDisplayed() }
//        waitFor(waitTime) { page.overview.errorRabbitMqValue.isDisplayed() }
//    }
//
//    def "Verify Error messages of Add Configuration for CUSTOM"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("CUSTOM")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//        waitFor(waitTime) { page.overview.exportConnectorClass.isDisplayed() }
//
//        when: 'Save button is clicked'
//        page.overview.save.click()
//        then: 'Error messages are displayed'
//        waitFor(waitTime) { page.overview.errorStream.isDisplayed() }
//        waitFor(waitTime) { page.overview.errorExportConnectorClass.isDisplayed() }
//    }
//
//    def "Verify Error messages of Add Configuration for ELASTICSEARCH"() {
//        when: 'Open Add ConfigurationPopup'
//        page.overview.openAddConfigurationPopup()
//        page.overview.textType.value("ELASTICSEARCH")
//        then: 'Check elements'
//        waitFor(waitTime) { page.overview.addProperty.isDisplayed() }
//        waitFor(waitTime) { page.overview.save.isDisplayed() }
//        waitFor(waitTime) { page.overview.cancel.isDisplayed() }
//
//        waitFor(waitTime) { page.overview.endpointES.value().equals("endpoint") }
//        waitFor(waitTime) { page.overview.endpointESValue.isDisplayed() }
//
//
//        when: 'Save button is clicked'
//        page.overview.save.click()
//        then: 'Error messages are displayed'
//        waitFor(waitTime) { page.overview.errorStream.isDisplayed() }
//        waitFor(waitTime) { page.overview.errorEndpointESValue.isDisplayed() }
//    }

    def "Verify Add Configuration for FILE created"() {
       // String fileTestName     = page.overview.getFileTestName()
        //String fileValueOne     = page.overview.getFileValueOne()
        //String fileValueTwo     = page.overview.getFileValueTwo()
        //String fileValueThree   = page.overview.getFileValueThree()

        when: 'Open Add ConfigurationPopup'
        page.overview.openAddConfigurationPopup()
        page.overview.textType.value("FILE")
        then: 'Check elements'
        page.overview.addProperty.isDisplayed()
        page.overview.save.isDisplayed()
        page.overview.cancel.isDisplayed()
        page.overview.type.value().equals("type")
        page.overview.nonce.value().equals("nonce")
        page.overview.outdir.value().equals("outdir")
        page.overview.typeValue.isDisplayed()
        page.overview.nonceValue.isDisplayed()
        page.overview.outdirValue.isDisplayed()

        when: 'Provide values for add configuration'
        page.overview.stream.value("fileTest")
        page.overview.typeValue.value("test")
        page.overview.nonceValue.value("test")
        page.overview.outdirValue.value("test")
        then: 'Click Save'
        //page.overview.clickSave()
        int count = 0
        while(count<numberOfTrials) {
            count++
            try {
                page.overview.save.click()
                !page.overview.save.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        int count_next = 0
        while(count_next<numberOfTrials) {
            count_next++
            try {
                page.overview.confirmyesbtn.click()
                !page.overview.confirmyesbtn.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        when: 'Expand export'
       page.overview.expandExport()
        then: 'Display the created FILE'
        waitFor(10){page.overview.fileName.isDisplayed()}
        println("Configuration created")

        when: 'Edit button is displayed'
        page.overview.editExportConfiguration.isDisplayed()
        then: 'Click edit button'
        page.overview.editExportConfiguration.click()

        when: 'Delete Configuration is displayed'
        page.overview.deleteConfiguration.isDisplayed()
        then: 'Click delete configuration'
        page.overview.deleteExportConfiguration()

//        whor deleted'
//        try {en: 'Expand export'
//       // waitFor(10){page.overview.expandExport()}
//        then: 'Check f
//            waitFor(waitTime) { page.overview.fileName.isDisplayed() }
//        } catch(geb.waiting.WaitTimeoutException e) {
//            println("Configuration deleted")
//        }
    }

    def "Verify Add Configuration for JDBC created"() {
        String jdbcTestName = page.overview.getJdbcTestName()
        String jdbcValueOne = page.overview.getJdbcValueOne()
        String jdbcValueTwo = page.overview.getJdbcValueTwo()

        when: 'Open Add ConfigurationPopup'
        page.overview.openAddConfigurationPopup()
        page.overview.textType.value("JDBC")
        then: 'Check elements'
        page.overview.addProperty.isDisplayed()
        page.overview.save.isDisplayed()
        page.overview.cancel.isDisplayed()
        page.overview.jdbcdriver.value().equals("jdbcdriver")
        page.overview.jdbcurl.value().equals("jdbcurl")
        page.overview.jdbcdriverValue.isDisplayed()
        page.overview.jdbcurlValue.isDisplayed()

        when: 'Provide values for add configuration'
        page.overview.stream.value("jdbcTest")
        page.overview.jdbcdriverValue.value("value1")
        page.overview.jdbcurlValue.value("value2")
        then: 'Click Save'
        //page.overview.clickSave()
        int count = 0
        while(count<numberOfTrials) {
            count++
            try {
                page.overview.save.click()
                !page.overview.save.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        int count_next = 0
        while(count_next<numberOfTrials) {
            count_next++
            try {
                page.overview.confirmyesbtn.click()
                !page.overview.confirmyesbtn.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        when: 'Expand export'
        page.overview.expandExport()
        then: 'Display the created JDBC'
        waitFor(10){ page.overview.jdbcName.isDisplayed()}
        println("Configuration created")

        when: 'Edit button is displayed'
        page.overview.editExportConfiguration.isDisplayed()
        then: 'Click edit button'
        page.overview.editExportConfiguration.click()

        when: 'Delete Configuration is displayed'
        page.overview.deleteConfiguration.isDisplayed()
        then: 'Click delete configuration'
        page.overview.deleteExportConfiguration()

//        when: 'Expand export'
//        page.overview.expandExport()
//        then: 'Check for deleted'
//        try {
//            waitFor(20) { page.overview.jdbcName.isDisplayed() }
//        } catch(geb.waiting.WaitTimeoutException e) {
//            println("Configuration deleted")
//        }
    }

    def "Verify Add Configuration for KAFKA created"() {
        String kafkaTestName = page.overview.getKafkaTestName()
        String metadataValue = page.overview.getMetadataValue()

        when: 'Open Add ConfigurationPopup'
        page.overview.openAddConfigurationPopup()
        page.overview.textType.value("KAFKA")
        then: 'Check elements'
         page.overview.addProperty.isDisplayed()
         page.overview.save.isDisplayed()
        page.overview.cancel.isDisplayed()
        page.overview.metadatabroker.value().equals("metadata.broker.list")
        page.overview.metadatabrokerValue.isDisplayed()

        when: 'Provide values for add configuration'
        page.overview.stream.value("kafkaTest")
        page.overview.metadatabrokerValue.value("metadataValue")
        then: 'Click Save'
        //page.overview.clickSave()
        int count = 0
        while(count<numberOfTrials) {
            count++
            try {
                page.overview.save.click()
                !page.overview.save.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        int count_next = 0
        while(count_next<numberOfTrials) {
            count_next++
            try {
                page.overview.confirmyesbtn.click()
                !page.overview.confirmyesbtn.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        when: 'Expand export'
        page.overview.expandExport()
        then: 'Display the created KAFKA'
        waitFor(20){page.overview.kafkaName.isDisplayed()}
        println("Configuration created")

        when: 'Edit button is displayed'
        waitFor(10){page.overview.editExportConfiguration.isDisplayed()}
        then: 'Click edit button'
        page.overview.editExportConfiguration.click()

        when: 'Delete Configuration is displayed'
        page.overview.deleteConfiguration.isDisplayed()
        then: 'Click delete configuration'
        page.overview.deleteExportConfiguration()

//        when: 'Expand export'
//        waitFor(10){page.overview.expandExport()}
//        then: 'Check for deleted'
//        try {
//            waitFor(waitTime) { page.overview.kafkaName.isDisplayed() }
//        } catch(geb.waiting.WaitTimeoutException e) {
//            println("Configuration deleted")
//        }
    }

    def "Verify Add Configuration for HTTP created"() {
        String httpTestName = page.overview.getHttpTestName()
        String endValue = page.overview.getEndValue()

        when: 'Open Add ConfigurationPopup'
        page.overview.openAddConfigurationPopup()
        page.overview.textType.value("HTTP")
        then: 'Check elements'
        page.overview.addProperty.isDisplayed()
         page.overview.save.isDisplayed()
         page.overview.cancel.isDisplayed()
        page.overview.endpoint.value().equals("endpoint")
        page.overview.endpointValue.isDisplayed()

        when: 'Provide values for add configuration'
        page.overview.stream.value(httpTestName)
        page.overview.endpointValue.value(endValue)
        then: 'Click Save'
        //page.overview.clickSave()
        int count = 0
        while(count<numberOfTrials) {
            count++
            try {
                page.overview.save.click()
                !page.overview.save.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        int count_next = 0
        while(count_next<numberOfTrials) {
            count_next++
            try {
                page.overview.confirmyesbtn.click()
                !page.overview.confirmyesbtn.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        when: 'Expand export'
        page.overview.expandExport()
        then: 'Display the created HTTP'
        waitFor(10){page.overview.httpName.isDisplayed()}
        println("Configuration created")

        when: 'Edit button is displayed'
        page.overview.editExportConfiguration.isDisplayed()
        then: 'Click edit button'
        page.overview.editExportConfiguration.click()

        when: 'Delete Configuration is displayed'
        page.overview.deleteConfiguration.isDisplayed()
        then: 'Click delete configuration'
        page.overview.deleteExportConfiguration()

//        when: 'Expand export'
//
//        page.overview.expandExport()
//        then: 'Check for deleted'
//        try {
//            waitFor(waitTime) { page.overview.httpName.isDisplayed() }
//        } catch(geb.waiting.WaitTimeoutException e) {
//            println("Configuration deleted")
//        }
    }

    def "Verify Add Configuration for RABBITMQ/Verify Add Configuration for RABBITMQ broker.host created"() {
        String rabbitmqBrokerTestName = page.overview.getRabbitmqBrokerTestName()
        String brokerValue = page.overview.getBrokerValue()

        when: 'Open Add ConfigurationPopup'
        page.overview.openAddConfigurationPopup()
        page.overview.textType.value("RABBITMQ")
        then: 'Check elements'
        page.overview.addProperty.isDisplayed()
         page.overview.save.isDisplayed()
         page.overview.cancel.isDisplayed()
        page.overview.rabbitMqValue.isDisplayed()

        when: 'Provide values for add configuration'
        page.overview.stream.value(rabbitmqBrokerTestName)
        page.overview.rabbitMqValue.value(brokerValue)
        then: 'Click Save'
        //page.overview.clickSave()
        int count = 0
        while(count<numberOfTrials) {
            count++
            try {
                page.overview.save.click()
                !page.overview.save.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        int count_next = 0
        while(count_next<numberOfTrials) {
            count_next++
            try {
                page.overview.confirmyesbtn.click()
                !page.overview.confirmyesbtn.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        when: 'Expand export'
        page.overview.expandExport()
        then: 'Display the created RABBITMQ'
        waitFor(10) { page.overview.rabbitMqBrokerName.isDisplayed() }
        println("Configuration created")

        when: 'Edit button is displayed'
        page.overview.editExportConfiguration.isDisplayed()
        then: 'Click edit button'
        page.overview.editExportConfiguration.click()

        when: 'Delete Configuration is displayed'
        page.overview.deleteConfiguration.isDisplayed()
        then: 'Click delete configuration'
        page.overview.deleteExportConfiguration()

//        when: 'Expand export'
//        page.overview.expandExport()
//        then: 'Check for deleted'
//        try {
//            waitFor(waitTime) { page.overview.rabbitMqBrokerName.isDisplayed() }
//        } catch(geb.waiting.WaitTimeoutException e) {
//            println("Configuration deleted")
//        }
    }

    def "Verify Add Configuration for RABBITMQ amqp.uri created"() {
        String rabbitmqAmqpTestName = page.overview.getRabbitmqAmqpTestName()
        String amqpValue = page.overview.getAmqpValue()

        when: 'Open Add ConfigurationPopup'
        page.overview.openAddConfigurationPopup()
        page.overview.textType.value("RABBITMQ")
        page.overview.rabbitMq.value("amqp.uri")
        then: 'Check elements'
        page.overview.addProperty.isDisplayed()
        page.overview.save.isDisplayed()
        page.overview.cancel.isDisplayed()
         page.overview.rabbitMqValue.isDisplayed()

        when: 'Provide values for add configuration'
        page.overview.stream.value(rabbitmqAmqpTestName)
        page.overview.rabbitMqValue.value(amqpValue)
        then: 'Click Save'
        //page.overview.clickSave()
        int count = 0
        while(count<numberOfTrials) {
            count++
            try {
                page.overview.save.click()
                !page.overview.save.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        int count_next = 0
        while(count_next<numberOfTrials) {
            count_next++
            try {
                page.overview.confirmyesbtn.click()
                !page.overview.confirmyesbtn.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        when: 'Expand export'
        page.overview.expandExport()
        then: 'Display the created RABBITMQ'
        waitFor(10){ page.overview.rabbitMqAmqpName.isDisplayed()}
        println("Configuration created")

        when: 'Edit button is displayed'
        page.overview.editExportConfiguration.isDisplayed()
        then: 'Click edit button'
        page.overview.editExportConfiguration.click()

        when: 'Delete Configuration is displayed'
        page.overview.deleteConfiguration.isDisplayed()
        then: 'Click delete configuration'
        page.overview.deleteExportConfiguration()

//        when: 'Expand export'
//        page.overview.expandExport()
//        then: 'Check for deleted'
//        try {
//            waitFor(waitTime) { page.overview.rabbitMqAmpqName.isDisplayed() }
//        } catch(geb.waiting.WaitTimeoutException e) {
//            println("Configuration deleted")
//        }
    }

    def "Verify Add Configuration for CUSTOM created"() {
        String customTestName = page.overview.getCustomTestName()
        String customConnectorClass = page.overview.getCustomConnectorClass()

        when: 'Open Add ConfigurationPopup'
        page.overview.openAddConfigurationPopup()
        page.overview.textType.value("CUSTOM")
        then: 'Check elements'
        page.overview.addProperty.isDisplayed()
        page.overview.save.isDisplayed()
        page.overview.cancel.isDisplayed()
        page.overview.exportConnectorClass.isDisplayed()

        when: 'Provide values for add configuration'
        page.overview.stream.value(customTestName)
        page.overview.exportConnectorClass.value(customConnectorClass)
        then: 'Click Save'
       // page.overview.clickSave()
        int count = 0
        while(count<numberOfTrials) {
            count++
            try {
                page.overview.save.click()
                !page.overview.save.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        int count_next = 0
        while(count_next<numberOfTrials) {
            count_next++
            try {
                page.overview.confirmyesbtn.click()
                !page.overview.confirmyesbtn.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        when: 'Expand export'
        page.overview.expandExport()
        then: 'Display the created CUSTOM'
        waitFor(10) { page.overview.customName.isDisplayed() }
        println("Configuration created")

        when: 'Edit button is displayed'
        page.overview.editExportConfiguration.isDisplayed()
        then: 'Click edit button'
        page.overview.editExportConfiguration.click()

        when: 'Delete Configuration is displayed'
        page.overview.deleteConfiguration.isDisplayed()
        then: 'Click delete configuration'
        page.overview.deleteExportConfiguration()

//        when: 'Expand export'
//        page.overview.expandExport()
//        then: 'Check for deleted'
//        try {
//            waitFor(waitTime) { page.overview.customName.isDisplayed() }
//        } catch(geb.waiting.WaitTimeoutException e) {
//            println("Configuration deleted")
//        }
    }

    def "Verify Add Configuration for ELASTICSEARCH created"() {
        String elasticSearchTestName     = page.overview.getElasticSearchTestName()
        String elasticSearchValueOne     = page.overview.getElasticSearchValueOne()


        when: 'Open Add ConfigurationPopup'
        page.overview.openAddConfigurationPopup()
        page.overview.textType.value("ELASTICSEARCH")
        then: 'Check elements'
        page.overview.addProperty.isDisplayed()
        page.overview.save.isDisplayed()
        page.overview.cancel.isDisplayed()
        page.overview.endpointES.value().equals("endpoint")
        page.overview.endpointESValue.isDisplayed()

        when: 'Provide values for add configuration'
        page.overview.stream.value("elasticSearchTest")
        page.overview.endpointESValue.value("endpointValue")

        then: 'Click Save'
        //page.overview.clickSave()
        int count = 0
        while(count<numberOfTrials) {
            count++
            try {
                page.overview.save.click()
                !page.overview.save.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        int count_next = 0
        while(count_next<numberOfTrials) {
            count_next++
            try {
                page.overview.confirmyesbtn.click()
                !page.overview.confirmyesbtn.isDisplayed()
            } catch(geb.error.RequiredPageContentNotPresent e) {

            } catch(org.openqa.selenium.StaleElementReferenceException e) {

            }
        }

        when: 'Expand export'
        page.overview.expandExport()
        //int count = 0
        then: 'Display the created ELASTICSEARCH'
        waitFor(10){page.overview.elasticSearchName.isDisplayed()}
//        while(count<numberOfTrials) {
//            count++
//            try {
//                waitFor(waitTime) {  }
//                break
//            } catch(geb.waiting.WaitTimeoutException e) {}
//        }
//        println("Configuration created")

        when: 'Edit button is displayed'
        page.overview.editExportConfiguration.isDisplayed()
        then: 'Click edit button'
        page.overview.editExportConfiguration.click()

        when: 'Delete Configuration is displayed'
        page.overview.deleteConfiguration.isDisplayed()
        then: 'Click delete configuration'
        page.overview.deleteExportConfiguration()

//        when: 'Expand export'
//        page.overview.expandExport()
//        then: 'Check for deleted'
//        try {
//            waitFor(waitTime) { page.overview.elasticSearchName.isDisplayed() }
//        } catch(geb.waiting.WaitTimeoutException e) {
//            println("Configuration deleted")
//        }
    }
//
    def "Verify Add Property in Export Configuration"(){

        when: 'Open Add ConfigurationPopup'
        page.overview.openAddConfigurationPopup()
        page.overview.textType.value("KAFKA")
        then: 'Check elements'
         page.overview.addProperty.isDisplayed()
        page.overview.save.isDisplayed()
        page.overview.cancel.isDisplayed()
        page.overview.metadatabroker.value().equals("metadata.broker.list")
        page.overview.metadatabroker.isDisplayed()

        when: 'Add property is clicked'
        page.overview.addProperty.click()
        then: 'New text and value fields are displayed'
        page.overview.newTextField.isDisplayed()
        page.overview.newValueField.isDisplayed()
        page.overview.deleteFirstProperty.isDisplayed()
        when: 'Provide values for add configuration'
        page.overview.stream.value("kafkaTest")
        page.overview.metadatabrokerValue.value("metadataValue")
        page.overview.newTextField.value("value1")
        page.overview.newValueField.value("value2")
        then: 'Click Save'
        page.overview.clickSave()
        when: 'Expand export'
        page.overview.expandExport()
        then: 'Display the created KAFKA'
        waitFor(waitTime) { page.overview.kafkaName.isDisplayed() }
        println("Configuration created")


    }

    def "Verify Error messages of Add Property in Export Configuration"() {
        when: 'Open Add ConfigurationPopup'
        page.overview.openAddConfigurationPopup()
        page.overview.textType.value("KAFKA")
        then: 'Check elements'
        page.overview.addProperty.isDisplayed()
        page.overview.save.isDisplayed()
        page.overview.cancel.isDisplayed()
        page.overview.metadatabroker.value().equals("metadata.broker.list")
        page.overview.metadatabroker.isDisplayed()

        when: 'Add property is clicked'
        page.overview.addProperty.click()
        then: 'New text and value fields are displayed'
        page.overview.newTextField.isDisplayed()
        page.overview.newValueField.isDisplayed()
        page.overview.deleteFirstProperty.isDisplayed()
        when: 'Save button is clicked'
        page.overview.save.click()
        then: 'Error messages are displayed'
        page.overview.errorPropertyName1.isDisplayed()
        page.overview.errorPropertyValue1.isDisplayed()

    }
}