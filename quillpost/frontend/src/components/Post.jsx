import React from 'react'
import { Link } from 'react-router-dom'
import {Card, CardBody, CardText, Button} from 'reactstrap'

function Post({post = {title : "This is default post title", content : "This is default post content"}}) {
  return (
    
    <Card className="border-0 shadow-sm mt-3">
        <CardBody>
            <h1>{post.title}</h1>
            <CardText dangerouslySetInnerHTML={{__html: post.content.substring(0,100)+"...." }}>
            </CardText>

            <div>
                <Link className="btn btn-secondary" to = {'/posts/'+post.postId}>Read More</Link>
            </div>
        </CardBody>
    </Card>
  )
}

export default Post