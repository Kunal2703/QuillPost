import { Card, CardBody, Container, CardTitle, CardText } from "reactstrap";
import Base from "../components/Base";

const About = () => {
    return (
        <Base>
            <Container>
                <Card>
                    <CardBody>
                        <CardTitle tag = "h1" className="text-center">About Us</CardTitle>
                        <hr />
                        <CardText>
                        Microservices is a modern architectural approach to designing and building 
                        software applications. It represents a departure from the traditional 
                        monolithic software architecture, where an entire application is developed 
                        as a single, tightly-coupled entity. Instead, microservices break down an 
                        application into a collection of small, independent, and loosely-coupled 
                        services that can be developed, deployed, and scaled independently.

                        <hr />

                        Each microservice is responsible for a specific business capability or 
                        function and communicates with other services through well-defined APIs 
                        or protocols. This decoupling of services allows for greater flexibility,
                        agility, and scalability in software development. Developers can work on 
                        individual microservices without impacting the entire application, 
                        making it easier to maintain and update software.

                        <hr />

                        One of the key advantages of microservices is that it enables organizations 
                        to adapt quickly to changing requirements and scale their applications more 
                        efficiently. It also promotes a DevOps culture, where development and operations 
                        teams collaborate closely to automate the deployment and management of microservices.
                        
                        <hr />
                        However, implementing a microservices architecture comes with its own set of 
                        challenges, such as managing service interdependencies, ensuring data consistency,
                        and monitoring the health of a distributed system. Nevertheless, for organizations
                        seeking to build resilient, scalable, and agile software solutions, microservices
                        represent a powerful and increasingly popular approach in the world of software 
                        development.

                        </CardText>

                        <hr />

                        <CardText>
                            Phasellus vel mi id dolor auctor tristique. Vestibulum vel justo in
                            dui tempor laoreet. Nulla facilisi. Pellentesque habitant morbi
                            tristique senectus et netus et malesuada fames ac turpis egestas.
                        </CardText>

                    </CardBody>
                </Card>
            </Container>
        </Base>
    );
};

export default About;